# Android Chat UI

This is meant to ease the life of any developer looking to add chat functionality into his/her Android application and wouldn't like to worry so much about setting up the UI.

This library is still in it's very early stages, but improvements would come over time.

**Note**: This is merely a UI library, messages being actually sent and recieved by your application still needs to be implemented.

![Image of Library in action]
(https://files.slack.com/files-tmb/T08K70NPN-F0HKRMB9V-4a3862e88f/screen_shot_2016-01-04_at_3.26.25_am_1024.png)

### Version
v0.1

### Installation

Add this to your build.gradle file's dependencies:


### Usage
Drop the `ChatView` in your XML layout as is shown below:
```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"

    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <co.intentservice.chatui.ChatView
        android:id='@+id/chat_view'
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
        
</RelativeLayout>
```

ChatView xml attributes you can set include:
```
<co.intentservice.chatui.ChatView
    xmlns:chatview="http://schemas.android.com/apk/res-auto"
    ...
    chatview:inputBarBackgroundColor=""    <!--format="color" /-->
    chatview:inputBarInsetLeft=""          <!--format="dimension" /-->
    chatview:inputBarInsetTop=""           <!--format="dimension" /-->
    chatview:inputBarInsetRight=""         <!--format="dimension" /-->
    chatview:inputBarInsetBottom=""        <!--format="dimension" /-->

    chatview:inputElevation=""             <!--format="dimension" /-->
    chatview:inputBackgroundColor=""       <!--format="color" /-->
    chatview:inputUseEditorAction=""       <!--format="boolean" /-->

    chatview:inputTextAppearance=""        <!--format="reference" /-->
    chatview:inputTextSize=""              <!--format="dimension" /-->
    chatview:inputTextColor=""             <!--format="color" /-->
    chatview:inputHintColor=""             <!--format="color" /-->

    chatview:sendBtnIcon=""                <!--format="reference" /-->
    chatview:sendBtnIconTint=""            <!--format="color" /-->
    chatview:sendBtnVisible=""             <!--format="boolean" /-->
    chatview:sendBtnElevation=""           <!--format="dimension" /-->
    chatview:sendBtnBackgroundTint=""      <!--format="color" /-->
    />
```

And then in your `Activity` or `Fragment` you can get the instance of the `ChatView` by doing:

```
ChatView chatView = (ChatView) findViewById(R.id.chat_view);
```

To send a message anytime a user of your application taps the send button, you'll need to supply an instance of `ChatView.ChatListener` an override the `sendMessage()` callback.

```
chatView.setChatListener(new ChatView.ChatListener() {
    @Override
    public void userStartedTyping() {

        // do something while user is typing
    }

    @Override
    public void userStoppedTyping() {

        // do something when user has stopped typing
    }

    @Override
    public void onMessageReceived(String message, long timestamp) {

        // do something when chat view receives a message
    }

    @Override
    public boolean sendMessage(String message, long timestamp) {

        // do something when the user hits the send button

        sendMessageToMyAwesomeServer();
        return true;
    }
});
```

You can also execute some functionality when a user is typing or when the user has stopped typing.

For conveinence a `ChatView.SimpleChatListener` is provided. Override only the methods you need.

Finally, messages can be "receieved" using `newMessage()` as shown:
```
chatView.newMessage("Hahaha"); // this will update to display the sent message in a chat bubble on the left of the screen.
```

### TODO
This is list of things that are in the works for this library:

# Theming
- Ability to theme chat bubbles
    - bubble Color (sent / received messages)
    - bubble Elevation
    - message TextAppearance (sent / received messages)
    - time stamp TextAppearance (sent / received messages)

# Functionality
- Ability to use custom item layout
- Ability to send and recieve multimedia messages like images, embedded locations and even videos.
- Ability to track and update individual messages (Useful to be able to show delivered/read/unread status or the like)

Of course, additions to this library aren't limited to the above.

### Contributing
We welcome any and all contributions, code cleanups and feature requests.

1. Check for open issues or open a fresh issue to start a discussion around a feature idea or a bug.
2. Fork the repository on GitHub to start making your changes to the master branch (or branch off of it).
3. Send a pull request and bug the maintainer until it gets merged and published. :).

