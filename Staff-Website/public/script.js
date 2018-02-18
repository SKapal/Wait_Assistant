// Set the configuration for your app
// TODO: Replace with your project's config object


var config = {
  apiKey: "AIzaSyDxm0KCVUSrX0ccT3vdHPnq5tOVgTo_AtQ",
  authDomain: "wait-assistant-v2-fb.firebaseapp.com",
  databaseURL: "https://wait-assistant-v2-fb.firebaseio.com/",
  //storageBucket: "bucket.appspot.com"
};
firebase.initializeApp(config);

// Get a reference to the database service
var database = firebase.database();

function writeUserData(emailAddress, fName, lName, pNumber) {
  firebase.database().ref('users/' + emailAddress).set({
    email: emailAddress,
    firstName: fName,
    lastName: lName,
    phoneNumber: pNumber,
  });

}
