ALTER TABLE `athletecompetition`
CHANGE COLUMN `competitonId` `competitionId`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `athleteId`;