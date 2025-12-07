const express = require('express');
const router = express.Router();
const messageController = require('../controllers/message.controller');




// Create a new message
router.post('/create', messageController.create);

// Get all messages
router.get('/', messageController.findAll);

// Get messages sent by a user
router.get('/sender/:sender', messageController.findBySender);

// Get messages received by a user
router.get('/recipient/:recipient', messageController.findByRecipient);

// Get a message by ID (must be after all static routes)
router.get('/:id', messageController.findById);

// Update a message by ID
router.put('/update/:id', messageController.update);

// Delete a message by ID
router.delete('/delete/:id', messageController.delete);

module.exports = router;
