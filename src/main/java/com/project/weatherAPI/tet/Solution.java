package com.project.weatherAPI.tet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class Solution {
    int winner = 0;
    boolean flag = true;
    boolean turn = true;
    public int[][] grid = new int[3][3];
    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();

    public boolean check(int[][] grid) {
        if(grid[0][0] == grid[1][0] && grid[2][0] == grid[0][0] && grid[0][0] != 0) return true;
        if(grid[0][1] == grid[1][1] && grid[2][1] == grid[0][1] && grid[0][1] != 0) return true;
        if(grid[0][2] == grid[1][2] && grid[2][2] == grid[0][2] && grid[0][2] != 0) return true;

        if(grid[0][0] == grid[0][1] && grid[0][2] == grid[0][0] && grid[0][0] != 0) return true;
        if(grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2] && grid[1][1] != 0) return true;
        if(grid[2][0] == grid[2][1] && grid[2][2] == grid[2][0] && grid[2][2] != 0) return true;

        if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[1][1] != 0) return true;
        return grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[1][1] != 0;
    }

    public String tictac() {
        while(flag) {
            int row = -1;
            int col = -1;
            if(turn) {
                if(list1.size() == 6) {
                    int r = list1.removeFirst();
                    int c = list1.removeFirst();
                    grid[r][c] = 0;
                }
                log.info("User1 Turn");
                log.info("Enter Row: ");
                row = new Scanner(System.in).nextInt();
                log.info("Enter Col: ");
                col = new Scanner(System.in).nextInt();
                grid[row][col] = 1;
                list1.add(row);
                list1.add(col);
                turn = false;
            } else {
                if(list2.size() == 6) {
                    int r = list2.removeFirst();
                    int c = list2.removeFirst();
                    grid[r][c] = 0;
                }
                log.info("User2 Turn");
                log.info("Enter Row: ");
                row = new Scanner(System.in).nextInt();
                log.info("Enter Col: ");
                col = new Scanner(System.in).nextInt();
                grid[row][col] = 2;
                list2.add(row);
                list2.add(col);
                turn = true;
            }

            log.info(Arrays.deepToString(grid));

            if(check(grid)) {
                flag = false;
                winner = (!turn) ? (1) : (2);
            }
        }

        log.info("Winner: {}", String.valueOf(winner));

        return (winner == 1) ? ("User1") : ("User2");
    }
}