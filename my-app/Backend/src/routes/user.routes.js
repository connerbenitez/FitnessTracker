// src/routes/user.routes.js
const express = require('express');
const router = express.Router();
const userController = require('../controllers/user.controller');

// Routes

// GET all users
router.get('/', userController.findAll);

// GET user by ID 
router.get('/:id', userController.findById);

// Create user
router.post('/create', userController.create);

// Login
router.post('/login', userController.login);

// Update password
router.post('/change_password', userController.updatePassword);

// Update user profile
router.put('/update', userController.update);

module.exports = router;
