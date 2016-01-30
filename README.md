# Android Chat UI

This is meant to ease the life of any developer looking to add chat functionality into his/her Android application and wouldn't like to worry so much about setting up the UI. This library is still in it's very early stages, but improvements would come over time.

Note: This is merely a UI library, messages being actually sent and recieved by your application still needs to be implemented.

![Image of Library in action]
(https://files.slack.com/files-tmb/T08K70NPN-F0HKRMB9V-4a3862e88f/screen_shot_2016-01-04_at_3.26.25_am_1024.png)

### Version
v0.1

### Installation

Add this to your build.gradle file's dependencies:

`compile 'co.devcenter.square:android-ui-library:0.1'`

### Usage
Drop the `ChatView` in your XML layout as is shown below:
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="co.devcenter.androiduilibraryproject.MainActivity">
    
    <co.devcenter.androiduilibrary.ChatView
        android:id='@+id/chat_view'
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
        
</RelativeLayout>
```

And then in your `Activity` or `Fragment` you can get the instance of the `ChatView` by doing: 

```
ChatView chatView = (ChatView) findViewById(R.id.chat_view);
```

To send a message anytime a user of your application taps the send button, you need to first get an instance of the `ChatView`'s `SendButton` then set an `OnClickListener`. Inside the listener, you can now call `chatView.sendMessage()` which will "send" whatever text the user has typed in. 

```
chatView.getSendButton().setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // your implementation of whatever code that would actually send the message
        chatView.sendMessage(); // this just updates the UI to display the sent message in a chat bubble on the right of the screen.
    }
});
```

Messages can be "receieved" using `.sendMessage(String message)` e.g:
```
chatView.receiveMessage("Hahaha"); // this will update to display the sent message in a chat bubble on the left of the screen.
```

Finally, you can execute some functionality when a user is typing or when the user has stopped typing by setting a `ChatViewEventListener`. e.g:
```
chatView.setEventListener(new ChatViewEventListener() {
    @Override
    public void userIsTyping() {
        //do something while user is typing
    }

    @Override
    public void userHasStoppedTyping() {
        //do something when user has stopped typing
    }
});
```

### TODO
This is list of things that are in the works for this library:

# Theming
- Ability to theme input area (made up of input textbox and send button and "others")
    - backgroundColor
    - elevation
    - Ability to theme input textbox
        - backgroundColor
        - inputTextAppearnce
    - Ability to theme send button
        - backgroundColor
        - sendIconTint
- Ability to theme chat bubbles
    - backgroundColor
    - itemElevation (maybe?)
    - primaryTextAppearance (For tha chat text)
    - secondaryTextAppearance (for time stamp and other info)
    - 
- Ability to theme chat list
    - backgroundColor

# Functionality
- Ability to send and recieve multimedia messages like images, embedded locations and even videos.
- Ability to use custom item layout
- 

Of course, additions to this library aren't limited to the above.

### Contributing
We welcome any and all contributions, code cleanups and feature requests.

1. Check for open issues or open a fresh issue to start a discussion around a feature idea or a bug.
2. Fork the repository on GitHub to start making your changes to the master branch (or branch off of it).
3. Send a pull request and bug the maintainer until it gets merged and published. :).

