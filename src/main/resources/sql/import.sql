DROP TABLE IF EXISTS "player";
CREATE TABLE "player" (
   "id" INT AUTO_INCREMENT,
   "name" VARCHAR(50) NOT NULL,
   "email" VARCHAR(70) NOT NULL,
   "password" VARCHAR(255),
   "is_registered" TINYINT NOT NULL DEFAULT 0,
   "registration_date" TIMESTAMP,
   "creation_date" TIMESTAMP NOT NULL,
   "modification_date" TIMESTAMP NOT NULL,
   PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "match";
CREATE TABLE "match" (
   "id" INT AUTO_INCREMENT,
   "owner_id" INT NOT NULL,
   "is_active" TINYINT NOT NULL,
   "creation_date" TIMESTAMP NOT NULL,
   "modification_date" TIMESTAMP NOT NULL,
   PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "play_type";
CREATE TABLE "play_type" (
   "id" INT AUTO_INCREMENT,
   "name" VARCHAR(50) NOT NULL,
   "base_points" INT NOT NULL,
   PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "play";
CREATE TABLE "play" (
   "id" INT AUTO_INCREMENT,
   "type_id" INT NOT NULL,
   "is_served" TINYINT NOT NULL,
   "points" INT NOT NULL,
   "match_id" INT NOT NULL,
   "player_id" INT NOT NULL,
   "creation_date" TIMESTAMP NOT NULL,
   "modification_date" TIMESTAMP NOT NULL,
   PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "match_player";
CREATE TABLE "match_player" (
   "id" INT AUTO_INCREMENT,
   "match_id" INT NOT NULL,
   "player_id" INT NOT NULL,
   "score" INT NOT NULL,
   "creation_date" TIMESTAMP NOT NULL,
   "modification_date" TIMESTAMP NOT NULL,
   PRIMARY KEY ("id")
);


ALTER TABLE "match" ADD CONSTRAINT "FK_87531f17-5658-45bd-8d09-3299a128a34e" FOREIGN KEY ("owner_id") REFERENCES "player"("id")  ;

ALTER TABLE "play" ADD CONSTRAINT "FK_0b933374-a449-4732-8346-7d2db07c52cf" FOREIGN KEY ("type_id") REFERENCES "play_type"("id")  ;

ALTER TABLE "play" ADD CONSTRAINT "FK_a2b8e10a-5c60-4d25-841a-e9ef0cecedad" FOREIGN KEY ("match_id") REFERENCES "match"("id")  ;

ALTER TABLE "play" ADD CONSTRAINT "FK_a351ac9d-ab87-4502-8494-6d27e869e446" FOREIGN KEY ("player_id") REFERENCES "player"("id")  ;

ALTER TABLE "match_player" ADD CONSTRAINT "FK_39283753-fef2-4b36-8559-8b9dbe11ec64" FOREIGN KEY ("match_id") REFERENCES "match"("id")  ;

ALTER TABLE "match_player" ADD CONSTRAINT "FK_42cf729f-4b85-48fd-9fa3-fe186cd2c8e0" FOREIGN KEY ("player_id") REFERENCES "player"("id")  ;
