BungeeKickMove
==============
This is a very simple plugin that will move a player whenever he gets kicked, depending on a whitelist or blacklist.
Configuration :
```yaml
mode: blacklist
servername: lobby
list:
- ban
- kick
message: "%kickmsg%"
```
`mode`  can either be blacklist or whitelist. 
In blacklist mode, player will always be moved to default server unless his
kick message contains one of the words/phrases in list. In whitelist mode, he will always be 
kicked unless his kick message contains one of the phrases in list.

`servername` is the name of the server to kick to.

`message` is the message sent to the player when he switches server. 
It can be spanned over multiple lines using the following syntax : 

```yaml
message: |
  "This is the first line"
  "%kickmsg%"
  "This is the third line"
``` 
`%kickmsg%` will be replaced with the actual message as sent by the server.
