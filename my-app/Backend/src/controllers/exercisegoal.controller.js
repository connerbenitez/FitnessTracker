"use strict";

const ExerciseGoal = require("../models/exercisegoal.model");

// CREATE
exports.create = (req, res) => {
    ExerciseGoal.create(req.body, (err, id) => {
        if (err) return res.status(500).send(err);
        res.json({ message: "Exercise goal created", exercise_goal_id: id });
    });
};

// FIND ALL
exports.findAll = (req, res) => {
    ExerciseGoal.findAll((err, goals) => {
        if (err) return res.status(500).send(err);
        res.json(goals);
    });
};

// FIND BY ID
exports.findById = (req, res) => {
    ExerciseGoal.findById(req.params.id, (err, goal) => {
        if (err) return res.status(500).send(err);
        if (goal.length === 0) return res.status(404).json({ message: "Goal not found" });
        res.json(goal[0]);
    });
};

// FIND BY USER
exports.findByUserId = (req, res) => {
    ExerciseGoal.findByUserId(req.params.user_id, (err, goals) => {
        if (err) return res.status(500).send(err);
        res.json(goals);
    });
};

// UPDATE
exports.update = (req, res) => {
    ExerciseGoal.update(req.params.id, req.body, (err, result) => {
        if (err) return res.status(500).send(err);
        res.json({ message: "Exercise goal updated", result });
    });
};

// DELETE
exports.delete = (req, res) => {
    ExerciseGoal.delete(req.params.id, (err, result) => {
        if (err) return res.status(500).send(err);
        res.json({ message: "Exercise goal deleted", result });
    });
};
