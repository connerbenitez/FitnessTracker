'use strict';
const dbConn = require('../../config/db.config');

class Message {
    constructor(message) {
        this.message_id = message.message_id;
        this.sender = message.sender;
        this.recipient = message.recipient;
        this.message = message.message;
    }

    static create(message, result) {
        const insertData = {
            sender: message.sender,
            recipient: message.recipient,
            message: message.message
        };
        dbConn.query("INSERT INTO message SET ?", insertData, (err, res) => {
            if (err) return result(err, null);
            result(null, res.insertId);
        });
    }

    static findById(id, result) {
        dbConn.query("SELECT * FROM message WHERE message_id = ?", [id], (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    static findAll(result) {
        dbConn.query("SELECT * FROM message", (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    static findBySender(sender, result) {
        dbConn.query("SELECT * FROM message WHERE sender = ?", [sender], (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    static findByRecipient(recipient, result) {
        dbConn.query("SELECT * FROM message WHERE recipient = ?", [recipient], (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    static update(message_id, newMessage, result) {
        dbConn.query("UPDATE message SET message = ? WHERE message_id = ?", 
            [newMessage.message, message_id], (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    static delete(message_id, result) {
        dbConn.query("DELETE FROM message WHERE message_id = ?", [message_id], (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }
}

module.exports = Message;
