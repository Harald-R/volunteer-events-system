var http = require('http');
var fs = require('fs');

var app = require('express')();
var server = require('http').Server(app);

/* loading socket.io */
var io = require('socket.io').listen(server);

/* try to connect with server 1*/
var socket = io.connect(server);
socket.emit("start", "LET'S GO!");

/* returns an array of all connected sockets */
var clientsSocketList = io.sockets.clients();

var clientsUsernameList=new ArrayList;
var clientsIdList=new ArrayList;

io.sockets.on('connection', function (socket, usernameList,idList) {
	
    // When the server 1 connects, a message will be sent
    socket.emit('message', 'You are connected!');
	
	for (var i=0; i<usernameList.length; i++) {
		clientsUsernameList.add(usernameList[i]);
    }
	for (var i=0; i<idList.length; i++) {
        clientsIdList.add(idList[i]);
    }

});

server.listen(8080);

for (var i=0; i<clientsSocketList.length; i++) {
	
	var app = require('express')();
	var server = require('http').Server(app);
	var socket = io.connect(server);
	
	// The other clients are told that someone new has arrived
	socket.broadcast.emit('message', 'messageFromServer1');
}



