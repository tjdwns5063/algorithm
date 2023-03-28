import java.util.*;

class Solution {
    val board = Array<Array<String>>(51) { Array<String>(51) { "EMPTY" }}
    val merges = ArrayList<HashSet<Pair<Int, Int>>>()
    val ans = mutableListOf<String>()
    fun solution(commands: Array<String>): Array<String> {
        // var answer: Array<String> = arrayOf<String>()
        
        for (command in commands) {
            val parsed = command.split(' ');
            
            if (parsed[0] == "UPDATE" && parsed.size == 4) {
                update(parsed)
            } else if (parsed[0] == "UPDATE" && parsed.size == 3) {
                update2(parsed)
            } else if (parsed[0] == "MERGE") {
                merge(parsed)
            } else if (parsed[0] == "UNMERGE") {
                unmerge(parsed)
            } else if (parsed[0] == "PRINT") {
                print(parsed)
            }
        }
        // printBoard(4)
        return ans.toTypedArray()
    }
    
    fun printBoard(n: Int) {
        for (i in 1..n) {
            for (j in 1..n) {
                print("${board[i][j]} ")
            }
            println()
        }
    }
    
    fun merge(parsed: List<String>) {
        val coord1 = Pair(parsed[1].toInt(), parsed[2].toInt())
        val coord2 = Pair(parsed[3].toInt(), parsed[4].toInt())
        
        val set1Index: Int? = merges.indices.find { coord1 in merges[it] }
        val set2Index: Int? = merges.indices.find { coord2 in merges[it] }
        
        if (set1Index == null && set2Index == null) {
            merges.add(hashSetOf(coord1, coord2))
        } else if (set1Index != null && set2Index != null) {
            if (set1Index == set2Index)
                return
            merges[set2Index].addAll(merges[set1Index])
            merges.removeAt(set1Index)
        } else if (set1Index != null) {
            merges[set1Index].add(coord2);
        } else if (set2Index != null) {
            merges[set2Index].add(coord1);
        }
        val merge = merges.find { coord1 in it }
        if (merge != null) {
            for (coord in merge) {
                board[coord.first][coord.second] = if (board[coord1.first][coord1.second] == "EMPTY") {
                    board[coord2.first][coord2.second];
                } else {
                    board[coord1.first][coord1.second];
                }
            }
        }
        
    }
    
    fun unmerge(parsed: List<String>) {
        val coord = Pair(parsed[1].toInt(), parsed[2].toInt())
        val value = board[coord.first][coord.second]
        
        for (merge in merges) {
            if (coord in merge) {
                for (c in merge) {
                    // println(c)
                    board[c.first][c.second] = 
                        if (c.first != coord.first || c.second != coord.second) "EMPTY" else value
                }
                merges.remove(merge)
                return
            }
        }
    }
    
    fun print(parsed: List<String>) {
        val coord = Pair(parsed[1].toInt(), parsed[2].toInt())
        
        ans += board[coord.first][coord.second]
    }
    
    fun update(parsed: List<String>) {
        val coord = Pair(parsed[1].toInt(), parsed[2].toInt())
        
        for (merge in merges) {
            if (coord in merge) {
                for (coord in merge) {
                    board[coord.first][coord.second] = parsed[3]
                }
                return
            }
        }
        board[coord.first][coord.second] = parsed[3]
    }
    
    fun update2(parsed: List<String>) {
        for (i in 1..50) {
            for (j in 1..50) {
                if (board[i][j] == "EMPTY")
                    continue
                if (board[i][j] == parsed[1]) {
                    board[i][j] = parsed[2]
                }
            }
        }
    }
}