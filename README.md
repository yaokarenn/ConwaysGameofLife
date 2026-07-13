# Conway's Game of Life - Java Simulator

## Description
This project is a fully functional, interactive implementation of Conway's Game of Life built in Java using the Processing (`PApplet`) graphics engine. It simulates cellular automata on a dynamic, wrapping grid where cells live, die, and evolve across generations based on their neighbors. 

The application launches in a paused state, allowing users to manually map out custom starting configurations or instantly randomize the grid before starting the simulation.

---

## How It Works (Code Architecture)

The project is structured across four main object-oriented Java classes that manage the logic, rendering, and user input:

* **Button.java:** The foundation class handling spatial positioning and boundary collision. It calculates whether the user's mouse cursor is hovering within a specific rectangular zone on the screen.
* **Cell.java:** Inherits from `Button` to represent an individual unit in the simulation. Each cell tracks its current state (alive or dead) and its age. Surviving cells dynamically change color based on how many generations they have existed, transitioning into deeper shades of blue as they get older.
* **Grid.java:** Inherits from `Cell` and acts as the world container. It initializes a 2D matrix (`ArrayList` of `ArrayList`s) of cells. It handles the mathematical logic for neighbor checks and state updates.
* **Main.java:** The core engine that extends `PApplet` to control the application lifecycle. It establishes a 1000x600 window canvas, runs a 500x500 grid at 10 frames per second, handles the pause state, and listens for keyboard and mouse events.

---

## Technical Features

### 1. Toroidal (Wrapping) Grid Boundaries
Unlike grids with hard edges where border cells have fewer neighbors, this simulator uses a toroidal map approach. If a cell checks for neighbors past the top, bottom, left, or right edges of the screen, the coordinates automatically wrap around to the opposite side. This allows patterns (like gliders) to travel infinitely across the screen without crashing into a wall.

### 2. Algorithmic Cell Rules
Every time the simulation steps forward, the grid scans all 8 surrounding neighbors for every single cell. It evaluates the classical rules of Conway's Game of Life simultaneously to generate the next generation:
* **Underpopulation:** Any live cell with fewer than 2 live neighbors dies.
* **Survival:** Any live cell with 2 or 3 live neighbors lives on to the next generation.
* **Overpopulation:** Any live cell with more than 3 live neighbors dies.
* **Reproduction:** Any dead cell with exactly 3 live neighbors becomes a live cell.

### 3. Progressive Cell Aging
To make the simulation more visually distinct, cells do not stay a static color. When a cell is born, it starts with a dark blue tint. For every generation that it successfully survives, its `cellAge` variable increments, causing its blue color channel to shift and visually signify established, stable structures. Dead cells are rendered as solid white.

---

## Controls & Usage

You interact with the simulator entirely through your keyboard and mouse:

* **Mouse Click & Release:** Toggle individual cell states. Clicking a white (dead) cell turns it black/alive, and clicking a live cell turns it back to white. This can be done while the game is paused to draw custom shapes.
* **'p' Key:** Toggles the pause state. The application starts paused so you can draw your patterns. Pressing **'p'** starts the simulation, and pressing it again freezes it.
* **'r' Key:** Instantly randomizes the grid. Every cell on the board is given a 50% chance to be alive or dead, creating an immediate sandbox of chaotic generation.
