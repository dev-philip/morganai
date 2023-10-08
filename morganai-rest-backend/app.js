const express = require('express');
const ip = require('ip');
const userService = require('./services/userService');
const userController = require('./controllers/userController');
require('dotenv').config();

const app = express();
const PORT = process.env.SERVER_PORT;

app.get('/', (req, res) => {
    res.send('Welcome to Morganai api endpoiints !');
});

app.use('/users', userController); //get all users
app.use('/users', userController); //get users by email
app.use('/ai', userController); //get all conversation
app.use('/ai', userController); //get conversation by id

// display to console userData
// userService.fetchUsers().then(users => console.log(users));

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
    console.log(`Server is running on ${ip.address()}:${PORT}`);
});