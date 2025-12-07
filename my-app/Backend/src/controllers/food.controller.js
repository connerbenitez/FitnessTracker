'use strict';

const Food = require('../models/food.model');

// Create a new food
exports.create = (req, res) => {
    if (!req.body || !req.body.food_name || !req.body.calories) {
        return res.status(400).json({ errors: true, message: "Please provide food name and calories" });
    }

    const newFood = {
        food_name: req.body.food_name,
        calories: req.body.calories
    };

    Food.create(newFood, (err, foodId) => {
        if (err) {
            console.log(err);
            return res.status(500).json({ errors: true, message: "An error occurred while creating the food" });
        }
        res.status(201).json({ errors: false, message: "Food created successfully", food_id: foodId });
    });
};

// Get all foods
exports.findAll = (req, res) => {
    Food.findAll((err, foods) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, data: foods });
    });
};

// Get food by ID
exports.findById = (req, res) => {
    const id = req.params.id;
    Food.findById(id, (err, food) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        if (food.length === 0) return res.status(404).json({ errors: true, message: "Food not found" });
        res.status(200).json({ errors: false, data: food });
    });
};

// Get food by name
exports.findByFoodName = (req, res) => {
    const food_name = req.params.food_name;
    Food.findByFoodName(food_name, (err, foods) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, data: foods });
    });
};

// Get food by calories
exports.findByCalories = (req, res) => {
    const calories = req.params.calories;
    Food.findByCalories(calories, (err, foods) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, data: foods });
    });
};

// Update a food
exports.update = (req, res) => {
    if (!req.body || !req.body.food_name || !req.body.calories) {
        return res.status(400).json({ errors: true, message: "Please provide updated food name and calories" });
    }

    const foodId = req.params.id;
    const updatedFood = {
        food_name: req.body.food_name,
        calories: req.body.calories
    };

    Food.update(foodId, updatedFood, (err, result) => {
        if (err) return res.status(500).json({ errors: true, message: err });
        res.status(200).json({ errors: false, message: "Food updated successfully" });
    });
};


