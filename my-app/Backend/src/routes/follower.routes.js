const express = require('express');
const router = express.Router();
const followerController = require('../controllers/follower.controller');

// Create a new follower relationship
router.post('/create', followerController.create);

// Get all followers of a user
router.get('/followers/:userId', followerController.findFollowers);

// Get all users a user is following
router.get('/following/:userId', followerController.findFollowing);

// Delete a follower relationship
router.delete('/delete', followerController.delete);


router.get('/', followerController.findAll);

module.exports = router;
