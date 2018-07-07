# Android Chat UI

This is meant to ease the life of any developer looking to add chat functionality into his/her Android application and wouldn't like to worry so much about setting up the UI.

This library is still in it's very early stages, but improvements would come over time.

**Note**: This is merely a UI library, messages being actually sent and recieved by your application still needs to be implemented.

![Image of Library in action](http://res.cloudinary.com/duswj2lve/image/upload/v1479837904/chatui_k3diqq.png)

### Version
v0.1.4

### Installation

Add this to your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Then add the dependency

```
dependencies {
	compile 'com.github.timigod:android-chat-ui:v0.1.4'
}
```

## Usage
Drop the ChatView in your XML layout as is shown below:

```
<co.intentservice.chatui.ChatView
	android:id="@+id/chat_view"
	android:layout_width="match_parent"
	android:layout_height="match_parents"
	<!-- Insert customisation options here -->
/>
```

Remember to add this attribute to your root layout.

```
xmlns:chatview="http://schemas.android.com/apk/res-auto"
```

And then in your Activity or Fragment you can get the instance of the ChatView by doing:

```
ChatView chatView = (ChatView) findViewById(R.id.chat_view);
```

### Customization

You can customize the appearance of this view by using the following attributes.

```
chatview:backgroundColor=""
chatview:inputBackgroundColor=""
chatview:inputUseEditorAction="" // true or false
chatview:inputTextAppearance=""
chatview:inputTextSize=""
chatview:inputTextColor=""
chatview:inputHint=""
chatview:inputHintColor=""
chatview:sendBtnIcon=""
chatview:sendBtnIconTint=""
chatview:sendBtnBackgroundTint=""

chatview:bubbleBackgroundRcv="" // color
chatview:bubbleBackgroundSend="" //color
chatview:bubbleElevation="" // "flat" or "elevated"

```

### Sending messages

You can detect when a user clicks the "send" action button by using the `OnSentMessageListener`.

```
chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener(){
	@Override
	public boolean sendMessage(ChatMessage chatMessage){
		// perform actual message sending 
		return true;
	}
});
```


In the method `sendMessage()`, you can now perform whatever logic  to send messages i.e make an HTTP request or send the message over a socket connection. 

Depending on whatever logic or validation you put in place, you may return `true` or `false`. `true` will update the `ChatView` with the message bubble and `false` will do the opposite.

### Receiving messages

You can use the `chatView.addMessage(ChatMessage message)` to add a "received" message to the UI. This obviously should be done after whatever mechanisms you have in place have actually received a message. 

You can use this method or `chatView.addMessages(ArrayList<ChatMessage> messages)` to add messages to the UI. 

The side the chat bubble will appear on is determined by the `Type` of the `ChatMessage`.

### Deleting messages

You can remove messages using `chatView.removeMessage(int position)` or `chatView.clearMessages()`

### The ChatMessage class

`ChatMessage` holds all the relevant data about your message: `String message`, `long timestamp` and `Type type`. `Type` is an enum that can either be `SENT` or `RECEIVED`.

It is the `Type` that determines what side of the UI the bubble will appear.

### Typing Listener

You can detect different states of the user's typing activity by using `TypingListener`.

```
chatView.setTypingListener(new ChatView.TypingListener(){
	@Override
	public void userStartedTyping(){
		// will be called when the user starts typing
	}
	
	@Override
	public void userStoppedTyping(){
		// will be called when the user stops typing
	}
});
```

### TODO
This is list of things that are in the works for this library:

# Theming
- message TextAppearance (sent / received messages)
- timestamp TextAppearance (sent / received messages)
- The ability to use an image as the background for the ChatView

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
