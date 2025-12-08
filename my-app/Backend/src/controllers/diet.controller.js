'use strict';

const Diet = require('../models/diet.model');

// CREATE
exports.create = (req, res) => {
    const newDiet = req.body;
    
    Diet.create(newDiet, (err, id) => {
        if (err) return res.status(500).json({ error: err });
        res.status(201).json({ message: "Diet entry created", diet_id: id });
    });
};

// GET ALL
exports.findAll = (req, res) => {
    Diet.findAll((err, diets) => {
        if (err) return res.status(500).json({ error: err });
        res.json(diets);
    });
};

// GET BY ID
exports.findById = (req, res) => {
    Diet.findById(req.params.diet_id, (err, diet) => {
        if (err) return res.status(500).json({ error: err });
        if (!diet) return res.status(404).json({ message: "Diet entry not found" });
        res.json(diet);
    });
};

// GET BY USER
exports.findByUserId = (req, res) => {
    Diet.findByUserId(req.params.user_id, (err, diets) => {
        if (err) return res.status(500).json({ error: err });
        res.json(diets);
    });
};

// GET BY FOOD
exports.findByFoodId = (req, res) => {
    Diet.findByFoodId(req.params.food_id, (err, diets) => {
        if (err) return res.status(500).json({ error: err });
        res.json(diets);
    });
};

// GET BY DATE
exports.findByDate = (req, res) => {
    Diet.findByDate(req.params.date, (err, diets) => {
        if (err) return res.status(500).json({ error: err });
        res.json(diets);
    });
};

// UPDATE
exports.update = (req, res) => {
    Diet.update(req.params.diet_id, req.body, (err, result) => {
        if (err) return res.status(500).json({ error: err });
        res.json({ message: "Diet entry updated" });
    });
};

// DELETE
exports.delete = (req, res) => {
    Diet.delete(req.params.diet_id, (err, result) => {
        if (err) return res.status(500).json({ error: err });
        res.json({ message: "Diet entry deleted" });
    });
};
