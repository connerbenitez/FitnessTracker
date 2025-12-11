"use strict";

const Exercise = require("../models/exercise.model");

// CREATE
exports.create = (req, res) => {
    Exercise.create(req.body, (err, id) => {
        if (err) return res.status(500).send(err);
        res.json({ message: "Exercise created", exercise_id: id });
    });
};

// FIND ALL
exports.findAll = (req, res) => {
    Exercise.findAll((err, exercise) => {
        if (err) return res.status(500).send(err);
        res.json(exercise);
    });
};

// FIND BY ID
exports.findById = (req, res) => {
    Exercise.findById(req.params.id, (err, exercise) => {
        if (err) return res.status(500).send(err);
        if (exercise.length === 0) return res.status(404).json({ message: "Exercise not found" });
        res.json(exercise[0]);
    });
};

// FIND BY USER
exports.findByUserId = (req, res) => {
    Exercise.findByUserId(req.params.user_id, (err, exercise) => {
        if (err) return res.status(500).send(err);
        res.json(exercise);
    });
};

// FIND BY TYPE
exports.findByType = (req, res) => {
    Exercise.findByType(req.params.type, (err, exercise) => {
        if (err) return res.status(500).send(err);
        res.json(exercise);
    });
};
exports.findSinceDate = (req, res) => {
    const { user_id, type, date } = req.params;

    Exercise.findSinceDate(user_id, type, date, (err, exercise) => {
        if (err) return res.status(500).send(err);
        res.json(exercise);
    });
};

// FIND BY DATE
exports.findByDate = (req, res) => {
    Exercise.findByDate(req.params.date, (err, exercise) => {
        if (err) return res.status(500).send(err);
        res.json(exercise);
    });
};

// UPDATE
exports.update = (req, res) => {
    Exercise.update(req.params.id, req.body, (err, result) => {
        if (err) return res.status(500).send(err);
        res.json({ message: "Exercise updated", result });
    });
};

// DELETE
exports.delete = (req, res) => {
    Exercise.delete(req.params.id, (err, result) => {
        if (err) return res.status(500).send(err);
        res.json({ message: "Exercise deleted", result });
    });
};
