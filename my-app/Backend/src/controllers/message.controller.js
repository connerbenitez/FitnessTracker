'use strict';

const Message = require('../models/message.model');

// Create a new message
exports.create = (req, res) => {
    if (!req.body || !req.body.sender || !req.body.recipient || !req.body.message) {
        return res.status(400).json({ errors: true, message: "Please provide all required fields" });
    }

    const newMessage = {
        sender: req.body.sender,
        recipient: req.body.recipient,
        message: req.body.message
    };

    Message.create(newMessage, (err, messageId) => {
        if (err) {
            console.log(err);
            return res.status(500).json({ errors: true, message: "An error occurred while creating the message" });
        }
        res.status(201).json({ errors: false, message: "Message sent successfully", message_id: messageId });
    });
};

// Get all messages
exports.findAll = (req, res) => {
    Message.findAll((err, messages) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, data: messages });
    });
};

// Get message by ID
exports.findById = (req, res) => {
    const id = req.params.id;
    Message.findById(id, (err, message) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        if (!message || message.length === 0) return res.status(404).json({ errors: true, message: "Message not found" });
        res.status(200).json({ errors: false, data: message });
    });
};

// Get messages sent by a user
exports.findBySender = (req, res) => {
    const sender = req.params.sender;
    Message.findBySender(sender, (err, messages) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, data: messages });
    });
};

// Get messages received by a user
exports.findByRecipient = (req, res) => {
    const recipient = req.params.recipient;
    Message.findByRecipient(recipient, (err, messages) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, data: messages });
    });
};

// Update a message
exports.update = (req, res) => {
    if (!req.body || !req.body.message) {
        return res.status(400).json({ errors: true, message: "Please provide the new message content" });
    }

    const messageId = req.params.id;
    const newMessage = { message: req.body.message };

    Message.update(messageId, newMessage, (err, result) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, message: "Message updated successfully" });
    });
};

// Delete a message
exports.delete = (req, res) => {
    const messageId = req.params.id;
    Message.delete(messageId, (err, result) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, message: "Message deleted successfully" });
    });
};
