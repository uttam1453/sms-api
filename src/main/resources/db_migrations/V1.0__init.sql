-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sms` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sms` DEFAULT CHARACTER SET utf8 ;
USE `sms` ;

-- -----------------------------------------------------
-- Table `sms`.`INSTITUTION_TYPE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`INSTITUTION_TYPE` ;

CREATE TABLE IF NOT EXISTS `sms`.`INSTITUTION_TYPE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT 'SCHOOL/INSTITION',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`SCHOOL_MST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`SCHOOL_MST` ;

CREATE TABLE IF NOT EXISTS `sms`.`SCHOOL_MST` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `active_flag` TINYINT NOT NULL DEFAULT 1,
  `grade_system` TINYINT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `school_type_fk_idx` (`type` ASC),
  CONSTRAINT `school_type_fk`
    FOREIGN KEY (`type`)
    REFERENCES `sms`.`INSTITUTION_TYPE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`ROLE_MST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`ROLE_MST` ;

CREATE TABLE IF NOT EXISTS `sms`.`ROLE_MST` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `active_flag` TINYINT NOT NULL DEFAULT 1,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`MENU_MST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`MENU_MST` ;

CREATE TABLE IF NOT EXISTS `sms`.`MENU_MST` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `menu_name` VARCHAR(45) NOT NULL,
  `menu_url` VARCHAR(45) NOT NULL,
  `active_flag` TINYINT NOT NULL DEFAULT 1,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`ROLE_MENU_MAPPING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`ROLE_MENU_MAPPING` ;

CREATE TABLE IF NOT EXISTS `sms`.`ROLE_MENU_MAPPING` (
  `role_id` INT NOT NULL,
  `menu_id` INT NOT NULL,
  `active_flag` TINYINT NOT NULL DEFAULT 1,
  `exempt_user` VARCHAR(200) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`, `menu_id`),
  INDEX `role_id_map_fk_idx` (`role_id` ASC),
  INDEX `menu_id_map_fk_idx` (`menu_id` ASC),
  CONSTRAINT `role_id_map_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `sms`.`ROLE_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `menu_id_map_fk`
    FOREIGN KEY (`menu_id`)
    REFERENCES `sms`.`MENU_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`USER_MST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`USER_MST` ;

CREATE TABLE IF NOT EXISTS `sms`.`USER_MST` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `school_id` INT NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `image_url` VARCHAR(100) NULL,
  `active_flag` TINYINT NOT NULL DEFAULT 1,
  `device_id` VARCHAR(200) NULL,
  `device_type` VARCHAR(45) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `school_id_user_fk_idx` (`school_id` ASC),
  CONSTRAINT `school_id_user_fk`
    FOREIGN KEY (`school_id`)
    REFERENCES `sms`.`SCHOOL_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`STUDENT_MST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`STUDENT_MST` ;

CREATE TABLE IF NOT EXISTS `sms`.`STUDENT_MST` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `student_name` VARCHAR(45) NOT NULL,
  `enrollment_no` INT NOT NULL,
  `mobile_no` INT NOT NULL,
  `email_id` VARCHAR(60) NOT NULL,
  `blood_group` VARCHAR(10) NOT NULL,
  `enrollment_date` TIMESTAMP NULL,
  `sex` ENUM('MALE', 'FEMALE') NOT NULL DEFAULT 'MALE',
  `DOB` TIMESTAMP NULL,
  `address` VARCHAR(150) NOT NULL,
  `fathers_name` VARCHAR(45) NOT NULL,
  `mothers_name` VARCHAR(45) NOT NULL,
  `fathers_profession` VARCHAR(45) NOT NULL,
  `mothers_profession` VARCHAR(45) NOT NULL,
  `created_by` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id_student_fk_idx` (`user_id` ASC),
  INDEX `created_by_student_fk_idx` (`created_by` ASC),
  CONSTRAINT `user_id_student_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `created_by_student_fk`
    FOREIGN KEY (`created_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`STAFF_MST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`STAFF_MST` ;

CREATE TABLE IF NOT EXISTS `sms`.`STAFF_MST` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `DOB` TIMESTAMP NULL,
  `POB` VARCHAR(45) NOT NULL COMMENT 'place of birth',
  `address` VARCHAR(150) NOT NULL,
  `mobile_no` INT NOT NULL,
  `email_id` VARCHAR(60) NOT NULL,
  `blood_group` VARCHAR(10) NOT NULL,
  `service_start_date` TIMESTAMP NULL,
  `sex` ENUM('MALE', 'FEMALE') NOT NULL DEFAULT 'MALE',
  `fathers_name` VARCHAR(45) NOT NULL,
  `mothers_name` VARCHAR(45) NOT NULL,
  `job_desc` VARCHAR(45) NULL,
  `last_date_servcie` TIMESTAMP NULL,
  `active_flag` TINYINT NOT NULL DEFAULT 1,
  `created_by` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id_staff_fk_idx` (`user_id` ASC),
  INDEX `created_by_staff_fk_idx` (`created_by` ASC),
  CONSTRAINT `user_id_staff_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `created_by_staff_fk`
    FOREIGN KEY (`created_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`USER_ROLE_MAPPING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`USER_ROLE_MAPPING` ;

CREATE TABLE IF NOT EXISTS `sms`.`USER_ROLE_MAPPING` (
  `role_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `user_id_map_fk_idx` (`user_id` ASC),
  INDEX `role_id_map_fk_idx` (`role_id` ASC),
  PRIMARY KEY (`role_id`, `user_id`),
  CONSTRAINT `user_id_user_map_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_id_user_map_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `sms`.`ROLE_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`CLASS_MST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`CLASS_MST` ;

CREATE TABLE IF NOT EXISTS `sms`.`CLASS_MST` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `school_id` INT NOT NULL,
  `class` ENUM('LKG', 'UKG', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12') NULL,
  `grade_system` TINYINT NOT NULL DEFAULT 0,
  `created_by` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `created_by_class_fk_idx` (`created_by` ASC),
  CONSTRAINT `created_by_class_fk`
    FOREIGN KEY (`created_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`CLASS_SECTION_MST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`CLASS_SECTION_MST` ;

CREATE TABLE IF NOT EXISTS `sms`.`CLASS_SECTION_MST` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `class_id` INT NOT NULL,
  `section` VARCHAR(45) NULL,
  `total_student` INT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `class_id_section_fk_idx` (`class_id` ASC),
  CONSTRAINT `class_id_section_fk`
    FOREIGN KEY (`class_id`)
    REFERENCES `sms`.`CLASS_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`STUDENT_CLASS_MAPPING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`STUDENT_CLASS_MAPPING` ;

CREATE TABLE IF NOT EXISTS `sms`.`STUDENT_CLASS_MAPPING` (
  `student_id` INT NOT NULL,
  `class_id` INT NOT NULL,
  `roll_no` VARCHAR(45) NOT NULL,
  `year` VARCHAR(10) NOT NULL,
  `current_class` TINYINT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `student_id_class_map_fk_idx` (`student_id` ASC),
  INDEX `class_id_class_map_fk_idx` (`class_id` ASC),
  PRIMARY KEY (`student_id`, `class_id`),
  CONSTRAINT `student_id_class_map_fk`
    FOREIGN KEY (`student_id`)
    REFERENCES `sms`.`STUDENT_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `class_id_class_map_fk`
    FOREIGN KEY (`class_id`)
    REFERENCES `sms`.`CLASS_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`ATTENDANCE_DATEWISE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`ATTENDANCE_DATEWISE` ;

CREATE TABLE IF NOT EXISTS `sms`.`ATTENDANCE_DATEWISE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `class_id` INT NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_present` INT NOT NULL DEFAULT 0,
  `total_absent` INT NOT NULL DEFAULT 0,
  `created_by` INT NOT NULL,
  `modified_by` INT NOT NULL,
  `is_archived` TINYINT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `class_id_attendance_fk_idx` (`class_id` ASC),
  INDEX `modified_by_attendance_fk_idx` (`modified_by` ASC),
  INDEX `created_by_attendance_fk_idx` (`created_by` ASC),
  CONSTRAINT `class_id_attendance_fk`
    FOREIGN KEY (`class_id`)
    REFERENCES `sms`.`CLASS_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `created_by_attendance_fk`
    FOREIGN KEY (`created_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `modified_by_attendance_fk`
    FOREIGN KEY (`modified_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`ATTENDANCE_DETAILS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`ATTENDANCE_DETAILS` ;

CREATE TABLE IF NOT EXISTS `sms`.`ATTENDANCE_DETAILS` (
  `attendance_dt_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `present_flag` TINYINT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `attendance_dt_detail_fk_idx` (`attendance_dt_id` ASC),
  INDEX `student_id_attend_det_fk_idx` (`student_id` ASC),
  PRIMARY KEY (`attendance_dt_id`, `student_id`),
  CONSTRAINT `attendance_dt_detail_fk`
    FOREIGN KEY (`attendance_dt_id`)
    REFERENCES `sms`.`ATTENDANCE_DATEWISE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `student_id_attend_det_fk`
    FOREIGN KEY (`student_id`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`ATTENDANCE_DETAILS_ARCHIVED`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`ATTENDANCE_DETAILS_ARCHIVED` ;

CREATE TABLE IF NOT EXISTS `sms`.`ATTENDANCE_DETAILS_ARCHIVED` (
  `attendance_dt_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `present_flag` TINYINT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `sttendance_dt_arch_det_fk_idx` (`attendance_dt_id` ASC),
  INDEX `student_id_arch_det_fk_idx` (`student_id` ASC),
  PRIMARY KEY (`attendance_dt_id`, `student_id`),
  CONSTRAINT `attendance_dt_arch_det_fk`
    FOREIGN KEY (`attendance_dt_id`)
    REFERENCES `sms`.`ATTENDANCE_DATEWISE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `student_id_arch_det_fk`
    FOREIGN KEY (`student_id`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`SUBJECT_MST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`SUBJECT_MST` ;

CREATE TABLE IF NOT EXISTS `sms`.`SUBJECT_MST` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_by` INT NOT NULL,
  `active_flag` TINYINT NOT NULL DEFAULT 1,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `created_by_subject_fk_idx` (`created_by` ASC),
  CONSTRAINT `created_by_subject_fk`
    FOREIGN KEY (`created_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`CLASS_TEACHERS_MAPPING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`CLASS_TEACHERS_MAPPING` ;

CREATE TABLE IF NOT EXISTS `sms`.`CLASS_TEACHERS_MAPPING` (
  `class_sec_id` INT NOT NULL,
  `teachers_id` INT NOT NULL,
  `is_class_teacher` TINYINT NOT NULL,
  `created_by` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `teach_id_class_teach_map_fk_idx` (`teachers_id` ASC),
  INDEX `created_by_class_teach_map_fk_idx` (`created_by` ASC),
  INDEX `class_id_class_teach_map_fk_idx` (`class_sec_id` ASC),
  PRIMARY KEY (`class_sec_id`, `teachers_id`),
  CONSTRAINT `class_id_class_teach_map_fk`
    FOREIGN KEY (`class_sec_id`)
    REFERENCES `sms`.`CLASS_SECTION_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `teach_id_class_teach_map_fk`
    FOREIGN KEY (`teachers_id`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `created_by_class_teach_map_fk`
    FOREIGN KEY (`created_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`SUBJECT_TEACHERS_MAPPING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`SUBJECT_TEACHERS_MAPPING` ;

CREATE TABLE IF NOT EXISTS `sms`.`SUBJECT_TEACHERS_MAPPING` (
  `subject_id` INT NOT NULL,
  `teachers_id` INT NOT NULL,
  `created_by` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `subject_id_sub_teach_map_fk_idx` (`subject_id` ASC),
  INDEX `teacher_id_sub_teach_map_fk_idx` (`teachers_id` ASC),
  INDEX `created_by_sub_teach_map_fk_idx` (`created_by` ASC),
  PRIMARY KEY (`subject_id`, `teachers_id`),
  CONSTRAINT `subject_id_sub_teach_map_fk`
    FOREIGN KEY (`subject_id`)
    REFERENCES `sms`.`SUBJECT_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `teacher_id_sub_teach_map_fk`
    FOREIGN KEY (`teachers_id`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `created_by_sub_teach_map_fk`
    FOREIGN KEY (`created_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`CLASS_SUBJECT_MAPPING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`CLASS_SUBJECT_MAPPING` ;

CREATE TABLE IF NOT EXISTS `sms`.`CLASS_SUBJECT_MAPPING` (
  `subject_id` INT NOT NULL,
  `class_sec_id` INT NOT NULL,
  `active_flag` TINYINT NOT NULL DEFAULT 1,
  `created_by` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `subject_id_class_sub_map_fk_idx` (`subject_id` ASC),
  INDEX `class_sec_id_class_sub_fk_idx` (`class_sec_id` ASC),
  INDEX `created_by_class_sub_fk_idx` (`created_by` ASC),
  PRIMARY KEY (`subject_id`, `class_sec_id`),
  CONSTRAINT `subject_id_class_sub_map_fk`
    FOREIGN KEY (`subject_id`)
    REFERENCES `sms`.`SUBJECT_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `class_sec_id_class_sub_fk`
    FOREIGN KEY (`class_sec_id`)
    REFERENCES `sms`.`CLASS_SECTION_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `created_by_class_sub_fk`
    FOREIGN KEY (`created_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`STUDENT_RESULT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`STUDENT_RESULT` ;

CREATE TABLE IF NOT EXISTS `sms`.`STUDENT_RESULT` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `class_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `exam_type` ENUM('CT', 'TEST', 'QUARTERLY', 'HALF_YEARLY', 'FINAL') NOT NULL DEFAULT 'CT',
  `year` VARCHAR(10) NOT NULL,
  `obtained_marks` DOUBLE NOT NULL DEFAULT 0.00,
  `maximum_marks` DOUBLE NOT NULL DEFAULT 0.00,
  `grade_obtained` VARCHAR(10) NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `class_id_result_fk_idx` (`class_id` ASC),
  INDEX `created_by_result_fk_idx` (`created_by` ASC),
  INDEX `student_id_result_fk_idx` (`student_id` ASC),
  CONSTRAINT `class_id_result_fk`
    FOREIGN KEY (`class_id`)
    REFERENCES `sms`.`CLASS_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `created_by_result_fk`
    FOREIGN KEY (`created_by`)
    REFERENCES `sms`.`USER_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `student_id_result_fk`
    FOREIGN KEY (`student_id`)
    REFERENCES `sms`.`STUDENT_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms`.`STUDENT_RESULT_DETAIL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms`.`STUDENT_RESULT_DETAIL` ;

CREATE TABLE IF NOT EXISTS `sms`.`STUDENT_RESULT_DETAIL` (
  `student_res_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  `max_marks` DOUBLE NOT NULL DEFAULT 0.00,
  `obtained_marks` DOUBLE NOT NULL DEFAULT 0.00,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `student_res_id_res_det_fk_idx` (`student_res_id` ASC),
  INDEX `subject_id_res_det_fk_idx` (`subject_id` ASC),
  PRIMARY KEY (`student_res_id`, `subject_id`),
  CONSTRAINT `student_res_id_res_det_fk`
    FOREIGN KEY (`student_res_id`)
    REFERENCES `sms`.`STUDENT_RESULT` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `subject_id_res_det_fk`
    FOREIGN KEY (`subject_id`)
    REFERENCES `sms`.`SUBJECT_MST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;