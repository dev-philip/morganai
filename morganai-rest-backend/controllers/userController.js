const express = require('express');
const router = express.Router();
const userService = require('../services//userService');

router.get('/', async (req, res) => {
    try {
        const users = await userService.fetchUsers();
        res.status(200).json(users);
    } catch (error) {
        res.status(500).json({ message: "Failed to retrieve users." });
    }
});


router.get('/conversation/', async (req, res) => {
    try {
        const users = await userService.fetchConversations();
        res.status(200).json(users);
    } catch (error) {
        res.status(500).json({ message: "Failed to retrieve users." });
    }
});

router.get('/conversation/:id', async (req, res) => {
    try {
        const userId = req.params.id;
        const user = await userService.fetchConversationById(userId);

        if (user) {
            res.status(200).json(user);
        } else {
            res.status(404).json({ message: "Conversation not found." });
        }
    } catch (error) {
        res.status(500).json({ message: "Failed to retrieve user." });
    }
});

router.get('/by/:id', async (req, res) => {
    try {
        const emailId = req.params.id;
        const user = await userService.fetchUsersByEmail(emailId);

        if (user) {
            res.status(200).json(user);
        } else {
            res.status(404).json({ message: "Conversation not found." });
        }
    } catch (error) {
        res.status(500).json({ message: "Failed to retrieve user." });
    }
});

module.exports = router;
