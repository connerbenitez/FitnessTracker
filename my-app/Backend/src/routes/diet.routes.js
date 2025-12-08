'use strict';

const express = require('express');
const router = express.Router();
const dietController = require('../controllers/diet.controller');

// CREATE
router.post('/', dietController.create);

// GET ALL
router.get('/', dietController.findAll);

// GET BY ID
router.get('/:diet_id', dietController.findById);

// GET BY USER
router.get('/user/:user_id', dietController.findByUserId);

// GET BY FOOD
router.get('/food/:food_id', dietController.findByFoodId);

// GET BY DATE
router.get('/date/:date', dietController.findByDate);

// UPDATE
router.put('/:diet_id', dietController.update);

// DELETE
router.delete('/:diet_id', dietController.delete);

module.exports = router;
