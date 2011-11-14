-- phpMyAdmin SQL Dump
-- version 3.3.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 27, 2011 at 01:46 AM
-- Server version: 5.1.54
-- PHP Version: 5.3.5-1ubuntu7.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mob`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment_owner`
--

CREATE TABLE IF NOT EXISTS `comment_owner` (
  `comment_id` int(100) NOT NULL AUTO_INCREMENT,
  `owner` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name_visible` binary(1) NOT NULL,
  `comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date_added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `comment_owner`
--


-- --------------------------------------------------------

--
-- Table structure for table `created_events`
--

CREATE TABLE IF NOT EXISTS `created_events` (
  `event_id` int(10) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `event_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `public_to_observe` binary(1) NOT NULL,
  `public_to_see` binary(1) NOT NULL,
  `observation_types` binary(5) NOT NULL,
  `score` int(3) NOT NULL,
  `number_of_scores` int(100) NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `created_events`
--


-- --------------------------------------------------------

--
-- Table structure for table `joined_events`
--

CREATE TABLE IF NOT EXISTS `joined_events` (
  `event_id` int(10) NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`event_id`,`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `joined_events`
--


-- --------------------------------------------------------

--
-- Table structure for table `observations_audio`
--

CREATE TABLE IF NOT EXISTS `observations_audio` (
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `event_id` int(10) NOT NULL,
  `supplied_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `comment_id` int(100) NOT NULL,
  `score` int(3) NOT NULL,
  `date_added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name_visible` binary(1) NOT NULL,
  PRIMARY KEY (`url`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `observations_audio`
--


-- --------------------------------------------------------

--
-- Table structure for table `observations_image`
--

CREATE TABLE IF NOT EXISTS `observations_image` (
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `event_id` int(10) NOT NULL,
  `supplied_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `comment_id` int(100) NOT NULL,
  `score` int(3) NOT NULL,
  `date_added` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name_visible` binary(1) NOT NULL,
  PRIMARY KEY (`url`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `observations_image`
--


-- --------------------------------------------------------

--
-- Table structure for table `observations_poll`
--

CREATE TABLE IF NOT EXISTS `observations_poll` (
  `poll_id` int(100) NOT NULL AUTO_INCREMENT,
  `event_id` int(10) NOT NULL,
  `supplied_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `comment_id` int(100) NOT NULL,
  `score` int(3) NOT NULL,
  `date_added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name_visible` binary(1) NOT NULL,
  PRIMARY KEY (`poll_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `observations_poll`
--


-- --------------------------------------------------------

--
-- Table structure for table `observations_text`
--

CREATE TABLE IF NOT EXISTS `observations_text` (
  `text_id` int(100) NOT NULL AUTO_INCREMENT,
  `event_id` int(10) NOT NULL,
  `supplied_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `comment_id` int(100) NOT NULL,
  `score` int(3) NOT NULL,
  `date_added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name_visible` binary(1) NOT NULL,
  PRIMARY KEY (`text_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `observations_text`
--


-- --------------------------------------------------------

--
-- Table structure for table `observations_video`
--

CREATE TABLE IF NOT EXISTS `observations_video` (
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `event_id` int(10) NOT NULL,
  `supplied_by` varchar(50) NOT NULL,
  `comment_id` int(100) NOT NULL,
  `score` int(3) NOT NULL,
  `date_added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name_visible` binary(1) NOT NULL,
  PRIMARY KEY (`url`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `observations_video`
--


-- --------------------------------------------------------

--
-- Table structure for table `oe_tags`
--

CREATE TABLE IF NOT EXISTS `oe_tags` (
  `event_id` int(10) NOT NULL,
  `tag_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`event_id`,`tag_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `oe_tags`
--


-- --------------------------------------------------------

--
-- Table structure for table `poll_answers`
--

CREATE TABLE IF NOT EXISTS `poll_answers` (
  `poll_id` int(100) NOT NULL,
  `answered_by` varchar(50) NOT NULL,
  `choice_id` int(100) NOT NULL,
  PRIMARY KEY (`poll_id`,`answered_by`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `poll_answers`
--


-- --------------------------------------------------------

--
-- Table structure for table `poll_choices`
--

CREATE TABLE IF NOT EXISTS `poll_choices` (
  `choice_id` int(100) NOT NULL AUTO_INCREMENT,
  `choice_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `poll_id` int(100) NOT NULL,
  PRIMARY KEY (`choice_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `poll_choices`
--


-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `activation_key` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--


-- --------------------------------------------------------

--
-- Table structure for table `users_can_cannot_observe`
--

CREATE TABLE IF NOT EXISTS `users_can_cannot_observe` (
  `event_id` int(10) NOT NULL,
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`event_id`,`user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_can_cannot_observe`
--


-- --------------------------------------------------------

--
-- Table structure for table `users_can_cannot_see`
--

CREATE TABLE IF NOT EXISTS `users_can_cannot_see` (
  `event_id` int(10) NOT NULL,
  `user` varchar(50) NOT NULL,
  PRIMARY KEY (`event_id`,`user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='if visibilty public, this table show restricted,else allowed';

--
-- Dumping data for table `users_can_cannot_see`
--

