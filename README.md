# Drone Pathfinding Simulator

A Java-based simulation that models autonomous drone navigation on a 2D grid using classic pathfinding algorithms. The system compares **Breadth-First Search (BFS)** and **A\*** to evaluate efficiency and performance.

---

## 📌 Features

- Grid-based environment with obstacles, start, and goal positions  
- Implemented **BFS** and **A\*** pathfinding algorithms  
- Real-time drone movement simulation  
- Interactive CLI for algorithm selection  
- Performance benchmarking (nodes explored, runtime)

---

## ⚙️ Tech Stack

- Java  
- Object-Oriented Programming (OOP)  
- Data Structures & Algorithms (BFS, A\*, Priority Queue)  
- File I/O  

---

## 🗺️ Example Map
```
S . . . X
. X . . .
. X . X .
. . . . .
X . . . G
```


- `S` = Start  
- `G` = Goal  
- `X` = Obstacle  
- `.` = Free space  

---

## 📊 Sample Results

| Algorithm | Nodes Explored | Avg Time |
|----------|---------------|----------|
| BFS      | ~20           | ~0.03 ms |
| A*       | ~9            | ~0.02 ms |

A* explores significantly fewer nodes while maintaining optimal path quality.

---

## ▶️ How to Run

```bash
git clone https://github.com/YOUR_USERNAME/drone-sim.git
cd drone-sim
javac src/*.java -d out
java -cp out Main
```

## 📁 Project Structure
```
src/
  Main.java
  Map.java
  MapLoader.java
  Drone.java
  BFSPathFinder.java
  AStarPathFinder.java
  PathFinder.java
  PathResult.java
Maps/
  map1.txt
```
