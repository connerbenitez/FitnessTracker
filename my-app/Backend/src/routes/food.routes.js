const express = require('express');
const router = express.Router();
const foodController = require('../controllers/food.controller');

// Create a new food
router.post('/create', foodController.create);

// Get all foods
router.get('/', foodController.findAll);

// Get food by ID
router.get('/:id', foodController.findById);

// Get food by name
router.get('/name/:food_name', foodController.findByFoodName);

// Get food by calories
router.get('/calories/:calories', foodController.findByCalories);

// Update a food by ID
router.put('/update/:id', foodController.update);


module.exports = router;
