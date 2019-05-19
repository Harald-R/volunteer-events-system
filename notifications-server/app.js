const io = require("socket.io"),
    server = io.listen(3001);

const CLIENT_DATA = 'client_data';
let clientsList = new Map();

// event fired every time a new client connects:
server.on("connection", (socket) => {
    console.info(`Client connected [id=${socket.id}]`);

    // Add client id to the list of connected clients
    socket.on(CLIENT_DATA, function (client) {
        clientsList.set(client.id, socket);
    });

    // When socket disconnects, remove it from the list
    socket.on("disconnect", () => {
        for (let [clientId, clientSocket] of clientsList) {
            if (clientSocket.id == socket.id) {
                clientsList.delete(clientId);
                break;
            }
        }
        console.info(`Client disconnected [id=${socket.id}]`);
    });

    // TODO: handle messages received from the backend server;
    //       forward them to the clients mentioned in the message
});
