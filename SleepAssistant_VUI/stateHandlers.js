'use strict';

var Alexa = require('alexa-sdk');
var audioData = require('./audioAssets');
var constants = require('./constants');

var stateHandlers = {
    playModeIntentHandlers : Alexa.CreateStateHandler(constants.states.START_MODE, {
        /*
         *  All Intent Handlers for state : PLAY_MODE
         */
        'LaunchRequest' : function () {
            /*
             *  Session d in PLAY_MODE STATE.
             *  If playback had finished during last session :
             *      Give welcome message.
             *      Change state to START_STATE to restrict user inputs.
             *  Else :
             *      Ask user if he/she wants to resume from last position.
             *      Change state to RESUME_DECISION_MODE
             */

             this.attributes['index'] = 0;
             this.attributes['offsetInMilliseconds'] = 0;
             this.attributes['loop'] = true;
             this.attributes['shuffle'] = false;
             this.attributes['playbackIndexChanged'] = true;
             this.attributes['playing'] = false;
             this.attributes['playbackFinished'] = false;
             this.handler.state = constants.states.START_MODE;

             var message = "Sleep Assistant here. How may I help you?";
             var reprompt = 'You can say, play audio, to begin.';

             this.response.speak(message).listen(reprompt);
             this.emit(':responseReady');


        },
        'ListAudio' : function () { controller.list.call(this) },
        'PlayAudio' : function () { controller.playHelper.call(this)},
        'LoopAudio' : function () { controller.loopAudioHelper.call(this)},
        'LoopAudioOff' : function () {controller.loopAudioOffHelper.call(this)},
        'AMAZON.NextIntent' : function () { controller.playNext.call(this) },
        'AMAZON.PreviousIntent' : function () { controller.playPrevious.call(this) },
        'AMAZON.PauseIntent' : function () { controller.stop.call(this) },
        'AMAZON.StopIntent' : function () { controller.stop.call(this) },
        'AMAZON.CancelIntent' : function () { controller.stop.call(this) },
        'AMAZON.ResumeIntent' : function () { controller.play.call(this) },
        'AMAZON.LoopOnIntent' : function () { controller.loopOn.call(this) },
        'AMAZON.LoopOffIntent' : function () { controller.loopOff.call(this) },
        'AMAZON.ShuffleOnIntent' : function () { controller.shuffleOn.call(this) },
        'AMAZON.ShuffleOffIntent' : function () { controller.shuffleOff.call(this) },
        'AMAZON.StartOverIntent' : function () { controller.startOver.call(this) },
        'AMAZON.HelpIntent': function(){
            if (this.attributes['playing'] === false) {
              var message = 'You can say things like play audio, play audio from playlist name, or list audio.';
              this.response.speak(message).listen(message);
              this.emit(':responseReady');
            } else {
              var message = 'You can say, Next or Previous to navigate through the playlist. ' +
                  'At any time, you can say Pause to pause the audio and Resume to resume.';
              this.response.speak(message).listen(message);
              this.emit(':responseReady');
            }
        },
        'SessionEndedRequest' : function () {
            // No session ended logic
        },
        'Unhandled' : function () {
            var message = 'Sorry, I could not understand. You can say, Next or Previous to navigate through the playlist.';
            this.response.speak(message).listen(message);
            this.emit(':responseReady');
        }
    }),
    remoteControllerHandlers : Alexa.CreateStateHandler(constants.states.START_MODE, {
        /*
         *  All Requests are received using a Remote Control. Calling corresponding handlers for each of them.
         */
        'PlayCommandIssued' : function () { controller.play.call(this) },
        'PauseCommandIssued' : function () { controller.stop.call(this) },
        'NextCommandIssued' : function () { controller.playNext.call(this) },
        'PreviousCommandIssued' : function () { controller.playPrevious.call(this) }
    })
};

module.exports = stateHandlers;

var controller = function () {
    return {
      playHelper: function () {

        //    this.handler.state = constants.states.PLAY_MODE;

            var playSlot = this.event.request.intent.slots.Playlist;
            if (playSlot && playSlot.value) {
                playSlot = playSlot.value.toLowerCase();
            }


            if (playSlot == "audio") {
                var response = "Please indicate a playlist by saying play playlist name.";
                this.response.speak(response).listen(response);
                this.emit(':responseReady');
            } else if (audioData[playSlot]) {
                this.attributes['currPlaylist'] = audioData[playSlot];
                this.attributes['playlistName'] = playSlot;
                this.attributes['playOrder'] = Array.apply(null, {length: audioData[playSlot].length}).map(Number.call, Number);
                controller.play.call(this)
            } else {
                var response = "Playlist not found. Try again.";
                this.response.speak(response).listen(response);
                this.emit(':responseReady');
            }
        },
        play: function () {

            this.attributes['playing'] = true;


             if (this.attributes['playbackFinished']) {
                // Reset to top of the playlist when reached end.
                this.attributes['index'] = 0;
                this.attributes['offsetInMilliseconds'] = 0;
                this.attributes['playbackIndexChanged'] = true;
                this.attributes['playbackFinished'] = false;
            }

            var token = String(this.attributes['playOrder'][this.attributes['index']]);
            var playBehavior = 'REPLACE_ALL';
            var offsetInMilliseconds = this.attributes['offsetInMilliseconds'];
            // Since play behavior is REPLACE_ALL, enqueuedToken attribute need to be set to null.
            this.attributes['enqueuedToken'] = null;

            var ind = this.attributes['index'];
            this.attributes['nextAudio'] = this.attributes['index'];
            var audio = this.attributes['currPlaylist'];
            var audioURL = audio.url[ind];

            var cardTitle = 'Playing ' + this.attributes['playlistName'];
            var cardContent = audio.title[ind];
            this.response.cardRenderer(cardTitle, cardContent, null);

            this.response.audioPlayerPlay(playBehavior, audioURL, token, null, offsetInMilliseconds);
            this.emit(':responseReady');
        },
        loopAudioHelper: function() {
            this.attributes['loopAudio'] = true;
            var message = 'Loop audio turned on.';
            this.response.speak(message);
            this.emit(':responseReady');
            // var token = String(this.attributes['playOrder'][this.attributes['index']]);
            // var playBehavior = 'REPLACE_ALL';
            // var audio = this.attributes['currPlaylist'];
            // //var podcast = audioData[this.attributes['playOrder'][this.attributes['index']]];
            // var offsetInMilliseconds = this.attributes['offsetInMilliseconds'];
            // // Since play behavior is REPLACE_ALL, enqueuedToken attribute need to be set to null.
            // this.attributes['enqueuedToken'] = null;
            //
            // var ind = this.attributes['index'];
            // var audio = this.attributes['currPlaylist'];
            // var audioURL = audio.url[ind];
            //
            // var cardTitle = 'Playing ' + audio.title[ind];
            // var cardContent = 'Playing ' + audio.title[ind];
            // this.response.cardRenderer(cardTitle, cardContent, null);
            //
            // this.response.audioPlayerPlay(playBehavior, audioURL, token, null, offsetInMilliseconds);
          //  this.emit(':responseReady');
        },
        loopAudioOffHelper: function() {
            this.attributes['loopAudio'] = false;
            var message = 'Loop audio turned off.';
            this.response.speak(message);
            this.emit(':responseReady');
        },
        stop: function () {
            /*
             *  Issuing AudioPlayer.Stop directive to stop the audio.
             *  Attributes already stored when AudioPlayer.Stopped request received.
             */
            this.response.audioPlayerStop();
            this.emit(':responseReady');
        },
        playNext: function () {
            /*
             *  Called when AMAZON.NextIntent or PlaybackController.NextCommandIssued is invoked.
             *  Index is computed using token stored when AudioPlayer.PlaybackStopped command is received.
             *  If reached at the end of the playlist, choose behavior based on "loop" flag.
             */
             // this.attributes['index'] = 0;
            this.attributes['index'] += 1;
            // Check for last audio file.
            if (this.attributes['index'] === this.attributes['currPlaylist'].url.length) {
                if (this.attributes['loop']) {
                    this.attributes['index'] = 0;
                } else {
                    // Reached at the end. Thus reset state to start mode and stop playing.
                //    this.handler.state = constants.states.START_MODE;

                    var message = 'You have reached at the end of the playlist.';
                    this.response.speak(message).audioPlayerStop();
                    return this.emit(':responseReady');
                }
            }
            // Set values to attributes.
            this.attributes['offsetInMilliseconds'] = 0;
            this.attributes['playbackIndexChanged'] = true;

            controller.play.call(this);
        },
        playPrevious: function () {
            /*
             *  Called when AMAZON.PreviousIntent or PlaybackController.PreviousCommandIssued is invoked.
             *  Index is computed using token stored when AudioPlayer.PlaybackStopped command is received.
             *  If reached at the end of the playlist, choose behavior based on "loop" flag.
             */
            this.attributes['index'] -= 1;
            // Check for last audio file.
            if (this.attributes['index'] < 0) {
                if (this.attributes['loop']) {
                    this.attributes['index'] = this.attributes['currPlaylist'].title.length - 1;
                } else {
                    // Reached at the end. Thus reset state to start mode and stop playing.
                //    this.handler.state = constants.states.START_MODE;

                    var message = 'You have reached at the start of the playlist.';
                    this.response.speak(message).audioPlayerStop();
                    return this.emit(':responseReady');
                }
            }
            // Set values to attributes.
            this.attributes['offsetInMilliseconds'] = 0;
            this.attributes['playbackIndexChanged'] = true;

            controller.play.call(this);
        },
        loopOn: function () {
            // Turn on loop play.
            this.attributes['loop'] = true;
            var message = 'Loop turned on.';
            this.response.speak(message);
            this.emit(':responseReady');
        },
        loopOff: function () {
            // Turn off looping
            this.attributes['loop'] = false;
            var message = 'Loop turned off.';
            this.response.speak(message);
            this.emit(':responseReady');
        },
        shuffleOn: function () {
            // Turn on shuffle play.
            this.attributes['shuffle'] = true;
            shuffleOrder((newOrder) => {
                // Play order have been shuffled. Re-initializing indices and playing first song in shuffled order.
                this.attributes['playOrder'] = newOrder;
                this.attributes['index'] = 0;
                this.attributes['offsetInMilliseconds'] = 0;
                this.attributes['playbackIndexChanged'] = true;
                controller.play.call(this);
            });
        },
        shuffleOff: function () {
            // Turn off shuffle play.
            if (this.attributes['shuffle']) {
                this.attributes['shuffle'] = false;
                // Although changing index, no change in audio file being played as the change is to account for reordering playOrder
                this.attributes['index'] = this.attributes['playOrder'][this.attributes['index']];
                this.attributes['playOrder'] = Array.apply(null, {length: audioData.length}).map(Number.call, Number);
            }
            controller.play.call(this);
        },
        startOver: function () {
            // Start over the current audio file.
            this.attributes['offsetInMilliseconds'] = 0;
            controller.play.call(this);
        },
        reset: function () {
            // Reset to top of the playlist.
            this.attributes['index'] = 0;
            this.attributes['offsetInMilliseconds'] = 0;
            this.attributes['playbackIndexChanged'] = true;
            controller.play.call(this);
        },
        list: function () {
          var response = "";
          var playSlot = this.event.request.intent.slots.Playlist; //ADD SLOT
          if (playSlot && playSlot.value) {
              playSlot = playSlot.value.toLowerCase(); //Oops I needa change the stuff to lowercase
          }

          if (playSlot == "audio") {
              response = "Please choose a playlist by saying list audio from playlist";
              this.response.speak(response).listen(response);
              this.emit(':responseReady');
          } else if (!audioData[playSlot]) {
              response = "Playlist not found."
          } else {
              for (var i = 0; i < audioData[playSlot].title.length; i++) {
                response += audioData[playSlot].title[i] + ", ";
              }
          }

          this.response.speak(response).listen(response);
          this.emit(':responseReady');
        }
    }
}();

function canThrowCard() {
    /*
     * To determine when can a card should be inserted in the response.
     * In response to a PlaybackController Request (remote control events) we cannot issue a card,
     * Thus adding restriction of request type being "IntentRequest".
     */
    if (this.event.request.type === 'IntentRequest' && this.attributes['playbackIndexChanged']) {
        this.attributes['playbackIndexChanged'] = false;
        return true;
    } else {
        return false;
    }
}

function shuffleOrder(callback) {
    // Algorithm : Fisher-Yates shuffle
    var array = Array.apply(null, {length: audioData.length}).map(Number.call, Number);
    var currentIndex = array.length;
    var temp, randomIndex;

    while (currentIndex >= 1) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;
        temp = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temp;
    }
    callback(array);
}
