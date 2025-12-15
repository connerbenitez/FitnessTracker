'use strict';
const dbConn = require('../../config/db.config');

class Exercise {
    constructor(exercise) {
        this.exercise_id = exercise.exercise_id;
        this.user_id = exercise.user_id;
        // this.exercise_goal_id = exercise.exercise_goal_id;
        this.type = exercise.type;
        this.distance = exercise.distance;
        this.start_time = exercise.start_time;
        this.end_time = exercise.end_time;
        this.date = exercise.date;
    }

    // CREATE
    static create(exercise, result) {
        const insertData = {
            
            user_id: exercise.user_id,
            // exercise_goal_id: exercise.exercise_goal_id,
            type: exercise.type,
            distance: exercise.distance,
            start_time: exercise.start_time,
            end_time: exercise.end_time,
            date: exercise.date
        };

        dbConn.query("INSERT INTO exercise SET ?", insertData, (err, res) => {
            if (err) return result(err, null);
            result(null, res.insertId);
        });
    }

    // FIND BY ID
    static findById(exercise_id, result) {
        dbConn.query(
            "SELECT * FROM exercise WHERE exercise_id = ?",
            [exercise_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND ALL
    static findAll(result) {
        dbConn.query("SELECT * FROM exercise", (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    // FIND BY USER
    static findByUserId(user_id, result) {
        dbConn.query(
            "SELECT * FROM exercise WHERE user_id = ?",
            [user_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND BY TYPE
    static findByType(type, result) {
        dbConn.query(
            "SELECT * FROM exercise WHERE type = ?",
            [type],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND BY DATE
    static findByDate(date, result) {
        dbConn.query(
            "SELECT * FROM exercise WHERE date = ?",
            [date],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }
    // Find a exercise type since a specific date
 static findSinceDate(user_id, type, date, result) {
    const sql = `
        SELECT *
        FROM exercise
        WHERE user_id = ? 
        AND type = ?
        AND date >= ?
        ORDER BY date DESC
    `;

    dbConn.query(sql, [user_id, type, date], (err, res) => {
        if (err) return result(err, null);
        result(null, res);
    });
}


    // UPDATE
    static update(exercise_id, exerciseData, result) {
        const sql = `
            UPDATE exercise 
            SET user_id = ?, type = ?, distance = ?, start_time = ?, 
                end_time = ?, date = ?
            WHERE exercise_id = ?
        `;

        const params = [
            exerciseData.user_id,
            exerciseData.type,
            exerciseData.distance,
            exerciseData.start_time,
            exerciseData.end_time,
            exerciseData.date,
            exercise_id
        ];

        dbConn.query(sql, params, (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    // DELETE
    static delete(exercise_id, result) {
        dbConn.query(
            "DELETE FROM exercise WHERE exercise_id = ?",
            [exercise_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }
}

module.exports = Exercise;
