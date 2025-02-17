## :dart: The Goal
Your program must destroy the enemy ships by shooting the closest enemy on each turn.

## :heavy_check_mark: Rules
On each start of turn (within the game loop), you obtain information on the two closest enemies:

- :arrow_left:***enemy1*** and :arrow_left:***dist1***: the name and the distance to enemy 1.
- :arrow_left:***enemy2*** and :arrow_left:***dist2***: the name and the distance to enemy 2.

Before your turn is over (end of the loop), output the value of either :arrow_up: ***enemy1*** or :arrow_up: ***enemy2*** to shoot the closest enemy.
