'use strict';

const User = require('../models/user.model');
const jwt = require("jsonwebtoken");
require("dotenv").config();

const Max_Age = 3 * 24 * 60 * 60;
const secretKey = process.env.JWT_SECRET || 'your_secret_key';

const createToken = (email) => {
    return jwt.sign({ email }, secretKey, { expiresIn: Max_Age });
};

exports.login = function (req, res) {
    const { email, password } = req.body;

    User.login(email, password, function (err, user) {
        if (err) return res.status(500).json({ errors: true, message: "Server error" });
        if (!user) return res.status(401).json({ errors: true, message: "Invalid email/password" });

        const token = createToken(user.email);

        res.cookie("jwt", token, {
            httpOnly: true,
            maxAge: Max_Age * 1000,
        });

        delete user.password;

        res.status(200).json({
            errors: false,
            token: token,
            user: user
        });
    });
};

exports.create = function (req, res) {
    const data = new User(req.body);

    User.findByEmail(data.email, function (err, existing) {
        if (err) return res.status(500).json({ errors: true });
        if (existing.length > 0)
            return res.status(400).json({ errors: true, message: "Email already exists" });

        User.create(data, function (err, insertId) {
            if (err) return res.status(500).json({ errors: true });
            res.status(201).json({ errors: false, message: "User created", id: insertId });
        });
    });
};

exports.update = function (req, res) {
    const user = new User(req.body);

    User.update(user, function (err, result) {
        if (err) return res.status(500).json({ errors: true });
        res.status(200).json({ errors: false, message: "User updated" });
    });
};

exports.updatePassword = function (req, res) {
    const { email, password } = req.body;

    User.updatePwd(password, email, function (err, result) {
        if (err) return res.status(500).json({ errors: true });

        if (result.affectedRows === 1)
            res.status(200).json({ errors: false, message: "Password updated" });
        else
            res.status(400).json({ errors: true, message: "Password update failed" });
    });
};

exports.findAll = function (req, res) {
    User.findAll(function (err, users) {
        if (err) return res.status(500).json({ errors: true });
        res.status(200).json({ errors: false, data: users });
    });
};

exports.findById = function (req, res) {
    User.findById(req.params.id, function (err, user) {
        if (err) return res.status(500).json({ errors: true });
        res.status(200).json({ errors: false, data: user });
    });
};

exports.findByUsername = function (req, res) {
    User.findByUsername(req.params.id, function (err, user) {
        if (err) return res.status(500).json({ errors: true });
        res.status(200).json({ errors: false, data: user });
    });
};
