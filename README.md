# Java-Airport-Simulation

## Overview
This project simulates the operations of Airport, focusing on optimizing landing clearance for incoming flights. It is designed to demonstrate principles of concurrent programming by modeling various processes that operate simultaneously while addressing synchronization challenges.

## Features
- **Single Runway Operations**: Only one runway is available for all landing and departing aircraft, ensuring controlled landings and takeoffs.
- **Ground Capacity Management**: A maximum of three airplanes can be on the airport grounds at any given time, preventing collisions.
- **Sequential Aircraft Procedures**: Each aircraft follows a defined sequence:
  1. **Landing**: Planes receive permission to land and touch down on the runway.
  2. **Taxiing**: Aircraft coast to their assigned gates.
  3. **Docking**: Planes dock at the gate, allowing passengers to disembark and new passengers to board.
  4. **Refueling**: A single refueling truck services the aircraft.
  5. **Takeoff**: Planes undock and return to the runway for departure.

## Concurrent Programming Dynamics
- The simulation utilizes multiple concurrent processes to represent the activities occurring at the airport:
  - **Disembarking and Embarking**: Passengers are managed concurrently at the gates while planes are refueled.
  - **Exclusive Resource Management**: The refueling process is synchronized, as only one aircraft can be refueled at a time.
- A congested scenario is simulated with two planes waiting to land and a third plane with fuel shortages requiring an emergency landing.

## Statistics Collection
At the end of the simulation, the ATC manager performs checks and prints statistics:
- Validates that all gates are empty.
- Reports on maximum, average, and minimum waiting times for planes.
- Counts the total number of planes served and passengers boarded.

## Implementation Details
- **Total Planes**: Six aircraft attempt to land during the simulation.
- **Arrival Timing**: A random number generator determines the arrival of new planes every **0, 1, 2, or 3 seconds**.
- **Capacity**: Each plane has a maximum capacity of **50 passengers**, ensuring that there are no capacity issues during boarding and disembarking.
