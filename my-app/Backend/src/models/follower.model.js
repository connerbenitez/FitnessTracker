'use strict';
const dbConn = require('../../config/db.config');

class Follower {
    constructor(follower) {
        this.following_user_id = follower.following_user_id; // The user being followed
        this.follower_user_id = follower.follower_user_id;   // The user who follows
    }

    // Add a new follower relationship
    static create(follower, result) {
        const insertData = {
            following_user_id: follower.following_user_id,
            follower_user_id: follower.follower_user_id
        };
        dbConn.query("INSERT INTO follower SET ?", insertData, (err, res) => {
            if (err) return result(err, null);
            result(null, res.insertId);
        });
    }

    // Get all followers of a specific user
    static findFollowers(userId, result) {
        dbConn.query(
            "SELECT follower_user_id FROM follower WHERE following_user_id = ?", 
            [userId], 
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // Get all users that a specific user is following
    static findFollowing(userId, result) {
        dbConn.query(
            "SELECT following_user_id FROM follower WHERE follower_user_id = ?", 
            [userId], 
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // Delete a follower relationship
    static delete(following_user_id, follower_user_id, result) {
        dbConn.query(
            "DELETE FROM follower WHERE following_user_id = ? AND follower_user_id = ?",
            [following_user_id, follower_user_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    static findAll(result) {
        dbConn.query("SELECT * FROM follower", (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }
}

module.exports = Follower;
