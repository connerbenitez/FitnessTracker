'use strict';
const dbConn = require('../../config/db.config');

class ExerciseGoal {
    constructor(exercisegoal) {
        this.exercise_goal_id = exercisegoal.exercise_goal_id;
        
        this.user_id = exercisegoal.user_id;
        this.description = exercisegoal.description;
        this.start_date = exercisegoal.start_date;
        this.end_date = exercisegoal.end_date;
        this.goal_distance = exercisegoal.goal_distance;
        this.goal_type = exercisegoal.goal_type;
        this.goal_time = exercisegoal.goal_time;
    }

    // CREATE
    static create(goal, result) {
        const insertData = {
            
            user_id: goal.user_id,
            description: goal.description,
            start_date: goal.start_date,
            end_date: goal.end_date,
            goal_distance: goal.goal_distance,
            goal_type: goal.goal_type,
            goal_time: goal.goal_time
        };

        dbConn.query("INSERT INTO exercisegoal SET ?", insertData, (err, res) => {
            if (err) return result(err, null);
            result(null, res.insertId);
        });
    }

    // FIND BY ID
    static findById(goal_id, result) {
        dbConn.query(
            "SELECT * FROM exercisegoal WHERE exercise_goal_id = ?",
            [goal_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND ALL
    static findAll(result) {
        dbConn.query("SELECT * FROM exercisegoal", (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    // FIND BY USER
    static findByUserId(user_id, result) {
        dbConn.query(
            "SELECT * FROM exercisegoal WHERE user_id = ?",
            [user_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // UPDATE
    static update(goal_id, goalData, result) {
        const sql = `
            UPDATE exercisegoal 
            SET exercise_id = ?, user_id = ?, description = ?, start_date = ?, end_date = ?, 
                goal_distance = ?, goal_type = ?, goal_time = ?
            WHERE exercise_goal_id = ?
        `;

        const params = [
            goalData.exercise_id,
            goalData.user_id,
            goalData.description,
            goalData.start_date,
            goalData.end_date,
            goalData.goal_distance,
            goalData.goal_type,
            goalData.goal_time,
            goal_id
        ];

        dbConn.query(sql, params, (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    // DELETE
    static delete(goal_id, result) {
        dbConn.query(
            "DELETE FROM exercisegoal WHERE exercise_goal_id = ?",
            [goal_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }
}

module.exports = ExerciseGoal;
