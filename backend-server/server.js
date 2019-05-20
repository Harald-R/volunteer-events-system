var express = require('express'),
    app = express(),
    port = process.env.PORT || 3000,
    mongoose = require('mongoose'),
    User = require('./api/models/users.model'),
    Event = require('./api/models/events.model'),
    bodyParser = require('body-parser'),
    cors = require('cors');
    // timeout = require('connect-timeout');;

// mongos instance url connection
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/backend-server-db');

// app.use(timeout(120000));
// app.use(cors);
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

var route_auth = require('./api/routes/auth.route');
var route_users = require('./api/routes/users.route');
var route_events = require('./api/routes/events.route');
route_auth(app);
route_users(app);
route_events(app);

app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
});

app.listen(port);

console.log('Backend server started on: ' + port);
