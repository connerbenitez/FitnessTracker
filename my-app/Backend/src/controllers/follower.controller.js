'use strict';

const Follower = require('../models/follower.model');

// Create a new follower relationship
exports.create = (req, res) => {
    if (!req.body || !req.body.follower_user_id || !req.body.following_user_id) {
        return res.status(400).json({ errors: true, message: "Provide both follower_user_id and following_user_id" });
    }

    const newFollower = {
        follower_user_id: req.body.follower_user_id,
        following_user_id: req.body.following_user_id
    };

    Follower.create(newFollower, (err, followerId) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(201).json({ errors: false, message: "Follower relationship created", follower_id: followerId });
    });
};

// Get all followers of a user
exports.findFollowers = (req, res) => {
    const userId = req.params.userId;

    Follower.findFollowers(userId, (err, followers) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, data: followers });
    });
};

// Get all users a user is following
exports.findFollowing = (req, res) => {
    const userId = req.params.userId;

    Follower.findFollowing(userId, (err, following) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, data: following });
    });
};

// Delete a follower relationship
exports.delete = (req, res) => {
    const { follower_user_id, following_user_id } = req.body;
    if (!follower_user_id || !following_user_id) {
        return res.status(400).json({ errors: true, message: "Provide both follower_user_id and following_user_id" });
    }

    Follower.delete(following_user_id, follower_user_id, (err, result) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, message: "Follower relationship deleted" });
    });
};


exports.findAll = (req, res) => {
    Follower.findAll((err, data) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, data });
    });
};
