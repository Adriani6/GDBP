This plugin takes all files and uploads them to your Google Drive account.
I have seen this request ages ago, and I have created the plugin but actually never published it as I see there's lack of few bits and pieces, however times came where I dust off few things and post them in case someone is willing to help out :)

The plugin is easy to use, if you follow the tutorial at the bottom of this thread.

To Devs: I have no idea how Refresh Tokens (in Google Drive) work, and I seen no working example of it in Java, hence I made it the way as it currently is.

Source Code | Plugin Download


Instructions are as follows,

You want to go to Google Developers Console, in there you create a new project, name it anything you wish and you can leave the project id as it is. After this is done, you'll be automatically taken to the projects page. In there, on your left side, you want to click on "APIs & auth" and look for Drive API in the list. Once you're in there, under the label "Drive API" You'll see a button saying "OFF" click it to enable the Drive. Once completed, from the menu on the left, click on Credentials.
Click on a red button saying "Create new Client ID" and select " Native Application" and then click the blue button.

At this point, you want to have the plugin installed on the server and have it generate its files.
In your plugins folder, look for "GoogleDrive", go there and you'll find config.yml, open it up and from Credentials page copy over the Client ID and Client Secret from the freshly generated Credentials.

Now reload the plugin, and that's you done. The above is only a one-off process, which once done doesn't need to be repeated.

How to back up & upload:

To back your files up, simply type:

/gdb start

This will zip all the files together and save them in your servers root folder/GoogleBackUp
Plugin will also generate a link in your plugins\GoogleDrive folder named url.yml
Open that up and open the link in your browser, you'll get a special code needed (token) to upload the file. (As I said before, I don't know how the Refresh Tokens work) This step will need to be repeated on every back up.

You'll get a message saying it has been ziped, and in your console, you will get all files listed (which have been zipped). I know it's quite spammy, but due to debugging I have currently left it on.

Once files been zipped, and you want to upload them, simply fire up another command:

/gdb upload <Token>

Token is what you get from the website found in url.yml.

Once you've completed those 2 command, you'll begin the upload of your backed up server to your Google Drive account.


Disclaimer:

I hold no responsibilities for any 'side-effect' this plugin might cause, it is in really early stages of its life.
I recommend running the plugin when there's a lot amount of players (I don't know if it will cause a lot of lag). The bigger the files, the longer it'll take to zip/upload remember !

If you do decide to give it a try, please report any issues and provide some feedback, that would be greatly appreciated :)

Enjoy.
