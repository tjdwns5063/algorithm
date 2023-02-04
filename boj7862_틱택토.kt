//time = 1sec
//memory = 128mb
//n = 9
//timeComp = 2^(n*2)

class Boj7862(comp: String) {
    val empty = CharArray(9) { '.' }
    val comp = comp
    val visited = BooleanArray(9) { false }
    var flag = false

    fun isGameEnd():Boolean {
        return (empty[0] != '.' && (empty[0] == empty[1] && empty[1] == empty[2])) || 
            (empty[3] != '.' && (empty[3] == empty[4] && empty[4] == empty[5])) ||
            (empty[6] != '.' && (empty[6] == empty[7] && empty[7] == empty[8])) || 
            (empty[0] != '.' && (empty[0] == empty[3] && empty[3] == empty[6])) ||
            (empty[1] != '.' && (empty[1] == empty[4] && empty[4] == empty[7])) || 
            (empty[2] != '.' && (empty[2] == empty[5] && empty[5] == empty[8])) ||
            (empty[0] != '.' && (empty[0] == empty[4] && empty[4] == empty[8])) || 
            (empty[2] != '.' && (empty[2] == empty[4] && empty[4] == empty[6]))
    }

    fun dfs(depth: Int) {
        if (depth >= 9 || isGameEnd()) {
            // println(empty)
            var idx = 0
            while (idx < 9) {
                if (empty[idx] != comp[idx])
                    break
                ++idx
            }
            if (idx == 9) {
                println("valid")
                flag = true
            }
            return
        }

        for (i in 0 until 9) {
            if (visited[i])
                continue
            if (flag)
                break
            if (depth % 2 == 0) {
                empty[i] = 'X'
                visited[i] = true
                dfs(depth+1)
                visited[i]= false
                empty[i] = '.'
            } else {
                empty[i] = 'O'
                visited[i] = true
                dfs(depth+1)
                visited[i]= false
                empty[i] = '.'
            }
        }
        
    }
    fun solution() {        
        dfs(0)
        if (!flag) {
            println("invalid")
        }
    }
}

fun main() {
    while (true) {
        val comp = readln()
        if (comp == "end")
            break
        Boj7862(comp).solution()
    }
}