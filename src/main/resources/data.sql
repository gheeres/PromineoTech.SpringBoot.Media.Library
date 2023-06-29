INSERT INTO `show` (show_name, show_description) 
VALUES 
  ('Ted Lasso', 'American college football coach Ted Lasso heads to London to manage AFC Richmond, a struggling English Premier League football team.'),
  ('Outlander', 'Claire Beauchamp Randall, a nurse in World War II, mysteriously goes back in time to Scotland in 1743. There, she meets a dashing Highland warrior and gets drawn into an epic rebellion.'),
  ('Bridgerton', 'The eight close-knit siblings of the Bridgerton family look for love and happiness in London high society. Inspired by Julia Quinn\'s best selling novels.'),
  ('The Flash', 'After being struck by lightning, Barry Allen wakes up from his coma to discover he\'s been given the power of super speed, becoming the Flash, and fighting crime in Central City.'),
  ('Stranger Things', 'When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.'),
  ('The Marvelous Mrs. Maisel','After her husband leaves her, young mother of two Miriam "Midge" Maisel discovers that she has a talent for stand-up comedy. Could this be her calling?')
;

INSERT INTO actor (actor_firstname, actor_lastname)
VALUES 
  ('Jason','Sudeikis'),
  ('Brett','Goldstein'),
  ('Brendan','Hunt'),
  ('Nick','Mohammed'),
  ('Hannah','Waddingham'),
  ('Juno','Temple')
;

INSERT INTO episode (episode_season, episode_episode, episode_name, episode_description, show_id) 
VALUES
  ('1', '1', 'Pilot', 'American football coach Ted Lasso is hired by a wealthy divorcée to coach English soccer team AFC Richmond.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('1', '2', 'Biscuits', 'It\'s Ted\'s first day of coaching, and fans aren\'t happy. He makes little headway but remains undeterred as the team plays its first match.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('1', '3', 'Trent Crimm: The Independent', 'To arrange an in-depth expose, Rebecca pairs cynical journalist Trent Crimm with Ted for a day. Ted and Roy venture into the community.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('1', '4', 'For the Children', 'Rebecca hosts the team\'s annual charity benefit, where Ted stages a reconciliation between Roy and Jamie.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('1', '5', 'Tan Lines', 'Ted reunites with his wife and son. Ted makes a bold choice and takes Jamie out of the game.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('1', '6', 'Two Aces', 'When Jamie refuses to train, Ted turns to talented new signing Dani Rojas-and the team is struck by an age-old curse.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('1', '7', 'Make Rebecca Great Again', 'Rebecca deals with her anniversary blues. Ted signs papers and has a small breakdown. An old friend of Rebecca introduces herself to Ted.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('1', '8', 'The Diamond Dogs', 'Ted and Roy are both having relationship problems. Ted consults the Diamond Dogs while Roy lays it on the line with Keeley.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('1', '9', 'All Apologies', 'After a series of mistakes on the pitch, pressure mounts to bench Roy. Rebecca finally reveals the truth to Ted.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('1', '10', 'The Hope That Kills You', 'Richmond plays a climactic match that will determine the fates of Ted and his club.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '1', 'Goodbye Earl', 'AFC Richmond brings in a sports psychologist to help the team overcome their unprecedented seven game tie-streak.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '2', 'Lavender', 'Ted is surprised by the reappearance of a familiar face; Roy tries out a new gig.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '3', 'Do the Right-est Thing', 'Rebecca has a special visitor shadow her at work. A player\'s return is not welcomed by the team.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '4', 'Carol of the Bells', 'It\'s Christmas in Richmond. Rebecca enlists Ted for a secret mission, Roy and Keeley search for a miracle and the Higginses open up their home.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '5', 'Rainbow', 'Nate learns how to be assertive from Keeley and Rebecca. Ted asks Roy for a favor. Roy reconsiders his future.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '6', 'The Signal', 'Ted is fired up that the new team dynamic seems to be working. But will they have a chance in the quarter final?', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '7', 'Headspace', 'With things turning around for Richmond, it\'s time for everyone to work on their issues - like Ted\'s discomfort, Nate\'s confidence and Roy\'s attention.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '8', 'Man City', 'Ted and Dr. Sharon realize they might have to meet each other halfway. Tensions are high as the team prepares for their FA Cup semi-final against Man City.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '9', 'Beard After Hours', 'After the semi-final defeat, Beard sets out on an all-night odyssey through London in order to collect his thoughts.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '10', 'No Weddings and a Funeral', 'Rebecca is stunned by a sudden loss. The team rallies to show their support, but Ted finds himself grappling with a piece of his past.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '11', 'Midnight Train to Royston', 'A billionaire football enthusiast from Ghana makes Sam an unbelievable offer. Ted plans something special for Dr. Sharon\'s last day with the team.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso')),
  ('2', '12', 'Inverting the Pyramid of', 'Richmond gets their final chance to win promotion as Ted deals with the fallout of Trent Crimm\'s painfully honest exposé.', (SELECT show_id FROM `show` WHERE show_name = 'Ted Lasso'))
;
