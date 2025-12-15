'use strict';

var dbConn = require('../../config/db.config');
const bcrypt = require('bcrypt');
const saltRounds = 10;

// User model
var User = function (user) {
    this.user_id = user.user_id;   
    this.username = user.username;
    this.password = user.password;
    this.first_name = user.first_name;
    this.last_name = user.last_name;
    this.email = user.email;
    this.start_date = user.start_date;
};

// CREATE USER
User.create = function (user, result) {
    bcrypt.hash(user.password, saltRounds, function (err, hash) {
        if (err) return result(err, null);

        user.password = hash;

        
        const insertData = {
            username: user.username,
            password: user.password,
            first_name: user.first_name,
            last_name: user.last_name,
            email: user.email,
            start_date: user.start_date
        };

        dbConn.query("INSERT INTO user SET ?", insertData, function (err, res) {
            if (err) return result(err, null);
            result(null, res.insertId);
        });
    });
};

// LOGIN USER
User.login = function (email, password, result) {
    const sql = "SELECT * FROM user WHERE email = ?";

    dbConn.query(sql, [email], function (err, res) {
        if (err) return result(err, null);
        if (res.length === 0) return result(null, null);

        bcrypt.compare(password, res[0].password, function (err, match) {
            if (match) return result(null, res[0]);
            return result(null, null);
        });
    });
};

// FIND USER BY EMAIL
User.findByEmail = function (email, result) {
    dbConn.query("SELECT * FROM user WHERE email = ?", [email], function (err, res) {
        if (err) return result(err, null);
        result(null, res);
    });
};

// FIND USER BY USERNAME
User.findByUsername = function (username, result) {
    dbConn.query("SELECT * FROM user WHERE username = ?", [username], function (err, res) {
        if (err) return result(err, null);
        result(null, res);
    });
};

// FIND USER BY ID
User.findById = function (id, result) {
    dbConn.query("SELECT * FROM user WHERE user_id = ?", [id], function (err, res) {
        if (err) return result(err, null);
        result(null, res);
    });
};

// GET ALL USERS
User.findAll = function (result) {
    dbConn.query("SELECT * FROM user", function (err, res) {
        if (err) return result(err, null);
        result(null, res);
    });
};

// UPDATE USER
User.update = function (user, result) {
    dbConn.query(
        "UPDATE user SET first_name=?, last_name=? WHERE email = ?",
        [user.first_name, user.last_name, user.email],
        function (err, res) {
            if (err) return result(err, null);
            result(null, res);
        }
    );
};

// UPDATE PASSWORD
User.updatePwd = function (password, email, result) {
    bcrypt.hash(password, saltRounds, function (err, hash) {
        if (err) return result(err, null);

        dbConn.query(
            "UPDATE user SET password=? WHERE email = ?",
            [hash, email],
            function (err, res) {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    });
};

// DELETE USER
User.delete = function (email, result) {
    dbConn.query("DELETE FROM user WHERE email = ?", [email], function (err, res) {
        if (err) return result(err, null);
        result(null, res);
    });
};

module.exports = User;
