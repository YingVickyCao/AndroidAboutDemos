package com.hades.android.example.room;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mock {
    public List<Student> buildStudents() {
        List<Student> list = new ArrayList<>();
        list.add(buildStudent(2, "Dale Carnegie"));
        list.add(buildStudent(3, "Harvard"));
        return list;
    }


    public Student buildStudent(int id, String studentName) {
        Student course = new Student();
        course.id = id;
        course.stuName = studentName;
        return course;
    }


    public Course buildCourse(int id, String courseName) {
        Course course = new Course();
        course.id = id;
        course.courseName = courseName;
        return course;
    }

    public List<Score> buildScores_v1() {
        List<Score> list = new ArrayList<>();
        list.add(buildScore(1, 1, 100, 1));
        list.add(buildScore(1, 2, 97, 2));
        return list;
    }

    public List<Score> buildScores_v3() {
        List<Score> list = new ArrayList<>();
        list.add(buildScore(2, 3, 85, 6));
//        list.add(buildScore(3, 1, 88));
//        list.add(buildScore(3, 2, 95));
//        list.add(buildScore(3, 3, 75));
        return list;
    }

    public Score buildScore(int courseId, int stuId, int _score, int temp) {
        Score score = new Score();
        score.courseId = courseId;
        score.stuId = stuId;
        score.score = _score;
        score.temp = temp;
        return score;
    }

    public String printCourse(List<Course> list) {
        String header = "Course:";
        String data = null;

        if (null == list || list.isEmpty()) {
            data = "\nnull";
        } else {
            for (Course item : list) {
                data += "\n" + String.format("%-10s", "(  " + item.id + " , " + item.courseName + "  )");
            }
        }
        return header + data;
    }

    public String printStudent(List<Student> list) {
        String header = "Student:";
        String data = "";

        if (null == list || list.isEmpty()) {
            data = "\n" + "null";
        } else {
            for (Student item : list) {
                data += "\n" + String.format("%-10s", "(  " + item.id + " , " + item.stuName + "  )");
            }
        }
        return header + data;
    }

    public String printScore(List<Score> list) {
        String header = "Score:";
        String data = "";

        if (null == list || list.isEmpty()) {
            data = "\n" + "null";
        } else {
            for (Score item : list) {
                data += "\n" + String.format("%-10s", "(  " + item.courseId + " , " + item.stuId + " , " + item.score + " , " + item.temp + "  )");
            }
        }
        return header + data;
    }

    public String printScore2(List<ScoreStuCourseTuple> list) {
        String header = "Score:";
        String data = "";

        if (null == list || list.isEmpty()) {
            data = "\n" + "null";
        } else {
            for (ScoreStuCourseTuple item : list) {
                data += "\n" + String.format("%-10s", "(  " + item.courseName + " , " + item.stuName + " , " + item.score + "  )");
            }
        }
        return header + data;
    }

    public String printScore(Score item) {
        String header = "Score:";
        String data = "";

        if (null == item) {
            data = "\n" + "null";
        } else {
            data += "\n" + String.format("%-10s", "(  " + item.courseId + " , " + item.stuId + " , " + item.score + " , " + item.temp + "  )");
        }
        return header + data;
    }

    public String printScoreTuple(List<ScoreTuple> list) {
        String header = "Score:";
        String data = "";

        if (null == list || list.isEmpty()) {
            data = "\n" + "null";
        } else {
            for (ScoreTuple item : list) {
                data += "\n" + String.format("%-10s", "(  " + item.courseId + " , " + item.score + "  )");
            }
        }
        return header + data;
    }

    public String printScore(Score[] list) {
        String header = "Score:";
        String data = "";

        if (null == list || list.length == 0) {
            data = "\n" + "null";
        } else {
            for (Score item : list) {
                data += "\n" + String.format("%-10s", "(  " + item.courseId + " , " + item.stuId + " , " + item.score + "  )");
            }
        }
        return header + data;
    }

    public String printLongs(long[] array) {
        if (null == array || array.length == 0) {
            return null;
        }
        return Arrays.toString(array);
    }

    public String printLongs(List<Long> list) {
        if (null == list || list.isEmpty()) {
            return null;
        }

        String data = "(";

        for (Long item : list) {
            data += item + ",";
        }
        data += ")";
        return data;
    }

    public List<Score> cursor2BeanList(Cursor cursor) {
        List<Score> list = new ArrayList<>();
        if (null == cursor) {
            return list;
        }
        while (cursor.moveToNext()) {
            Score score = new Score();
            score.courseId = cursor.getInt(cursor.getColumnIndex("course_id"));
            score.stuId = cursor.getInt(cursor.getColumnIndex("stu_id"));
            score.score = cursor.getInt(cursor.getColumnIndex("score"));
            score.temp = cursor.getInt(cursor.getColumnIndex("temp"));
            list.add(score);
        }
        return list;
    }


}
