'use strict';

const DietGoal = require('../models/dietgoal.model');

// CREATE
exports.create = (req, res) => {
    DietGoal.create(req.body, (err, id) => {
        if (err) return res.status(500).send(err);
        res.json({ message: "Diet Goal created", diet_goal_id: id });
    });
};

// FIND ALL
exports.findAll = (req, res) => {
    DietGoal.findAll((err, dietgoals) => {
        if (err) return res.status(500).send(err);
        res.json(dietgoals);
    });
};

// FIND BY ID
exports.findById = (req, res) => {
    DietGoal.findById(req.params.id, (err, dietgoals) => {
        if (err) return res.status(500).send(err);
        if (dietgoals.length === 0) return res.status(404).json({ message: "Diet Goal not found" });
        res.json(dietgoals[0]);
    });
};

// FIND BY USER
exports.findByUserId = (req, res) => {
    DietGoal.findByUserId(req.params.user_id, (err, dietgoals) => {
        if (err) return res.status(500).send(err);
        res.json(dietgoals);
    });
};

// FIND BY DIET ID
exports.findByDietId = (req, res) => {
    DietGoal.findByDietId(req.params.diet_id, (err, dietgoals) => {
        if (err) return res.status(500).send(err);
        res.json(dietgoals);
    });
};

// FIND BY START DATE
exports.findByStartDate = (req, res) => {
    DietGoal.findByStartDate(req.params.start_date, (err, dietgoals) => {
        if (err) return res.status(500).send(err);
        res.json(dietgoals);
    });
};

// FIND BY END DATE
exports.findByEndDate = (req, res) => {
    DietGoal.findByEndDate(req.params.end_date, (err, dietgoals) => {
        if (err) return res.status(500).send(err);
        res.json(dietgoals);
    });
};

// UPDATE
exports.update = (req, res) => {
    DietGoal.update(req.params.id, req.body, (err, result) => {
        if (err) return res.status(500).send(err);
        res.json({ message: "Diet Goal updated", result });
    });
};

// DELETE
exports.delete = (req, res) => {
    DietGoal.delete(req.params.id, (err, result) => {
        if (err) return res.status(500).send(err);
        res.json({ message: "Diet Goal deleted", result });
    });
};
