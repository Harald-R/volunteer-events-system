const io = require('socket.io-client'),
    notificationsServer = io.connect('http://localhost:3001');

module.exports = notificationsServer
