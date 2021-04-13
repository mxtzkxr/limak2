import java.lang.Exception
import java.lang.StringBuilder
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.round

fun main(){
    /*val base = 0.75
    val norm = 5.1776
    val k = approximationPower(base, norm, 0.0001)

    val F = Matrix(arrayOf(
        arrayOf(0.0, 0.25, 0.25, 0.25),
        arrayOf(0.0, -0.0625, 0.1875, 0.1875),
        arrayOf(0.0, -0.0468, -0.1093, 0.1407),
        arrayOf(0.0, -0.0351, -0.0819, -0.1444)
    ))

    val f = Matrix(arrayOf(
        arrayOf(3.0033),
        arrayOf(4.3925),
        arrayOf(-1.4362),
        arrayOf(-5.1776)
    ))

    println(getApproximation(k, F, f))*/

   /* var original = Matrix(arrayOf(
        arrayOf(0.984252, 0.0, 0.0,0.0),
        arrayOf(0.0553642, 1.5625, 0.0, 0.0),
        arrayOf(0.020677, 0.0425998, 0.97371, 0.0),
        arrayOf(0.179777, 0.177261,-0.0405712, 10.4167)
    ))

    var original2 = Matrix(arrayOf(
            arrayOf(0.0, 0.031, -0.033,-0.006),
            arrayOf(0.0, 0.0, 0.024, -0.008),
            arrayOf(0.0, 0.0, 0.0, 0.007),
            arrayOf(0.0, 0.0, 0.0, -0.0)
    ))


   var y = Matrix(arrayOf(
        arrayOf(13.181),
        arrayOf(-22.412),
        arrayOf(-12.892),
        arrayOf(-6.975)
    ))*/
/*
    val Atr = original.transposition()

    val originalN = Atr * original
    print(originalN)
    val yN = Atr * y
    val k = getGradApproximationPower(originalN, yN, 0.00088, 1.47348, 0.0001)
    val r = gradDescend(k, originalN, yN)
    val rs = original * r
    println(r)
    println(rs)*/
    /*val relevant = Matrix(arrayOf(
        arrayOf(1.216,-1.3367,-0.4954,1.1186),
        arrayOf(2.8446,-7.1545,-1.2337,3.9609),
        arrayOf(-0.2376,-0.7052,0.5022,1.0014),
        arrayOf(-2.5645,5.8562,1.9038,-3.1641)
    ))

    val D = Matrix(arrayOf(
        arrayOf(1.92, 0.23, -0.08, 0.21),
        arrayOf(0.05, -0.94, 0.23, -0.11),
        arrayOf(0.12, 0.21, -0.72, 0.32),
        arrayOf(0.27, -0.35, 0.57, -1.24)
    ))

    val B = D * relevant

    val newA = B * original
    val A_1 = newA.toLowerTriangle()
    val A_1_relv = Matrix(arrayOf(
        arrayOf(0.52, 0.0, 0.0, 0.0),
        arrayOf(0.028, -1.06, 0.0, 0.0),
        arrayOf(0.095, -0.31, -1.39, 0.0),
        arrayOf(0.15, 0.16, -0.64, -0.81)
    ))

    val A_2 = newA.toUpperWithout()

    val F = (A_1_relv * (-1.0) * A_2)


    val q = F.firstNorm().coerceAtMost(F.secondNorm())

    val newY = B * y
    val f = (A_1_relv * newY)
    val norm = getFirstApproximation(F, f).firstNorm()
    val k = approximationPower(q, norm, 0.0001)
    val r = getApproximation(k, F, f)
    println(r)
    println(original * r)*/

    /*val B = (original*original2).times(-1.0)
    val b = original*y
    var x = Matrix(arrayOf(arrayOf(0.0),
            arrayOf(0.0),
            arrayOf(0.0),
            arrayOf(0.0)))
    for (i in 0..7){
        x = B*x+b
    }
    println(x)

*/
/*
    var original = Matrix(arrayOf(
            arrayOf(2.8, 3.8, -3.2),
            arrayOf(2.5, -2.8, 3.3),
            arrayOf(6.5, -7.1, 4.8)
    ))

    var original2 = Matrix(arrayOf(
            arrayOf(0.16, 0.07, 0.06),
            arrayOf(0.15, 0.55,-0.28),
            arrayOf(0.007, 0.7, -0.3)
    ))


    var y = Matrix(arrayOf(
            arrayOf(4.5),
            arrayOf(7.1),
            arrayOf(6.3)
    ))*/
    var original = Matrix(arrayOf(
            arrayOf(1.0, 0.0, 0.0),
            arrayOf(0.02455, 1.0, 0.0),
            arrayOf(-0.218524, -0.23194, 1.0)
    ))

    var original2 = Matrix(arrayOf(
            arrayOf(0.0, 0.01382, -0.00691),
            arrayOf(0.0, 0.0,0.00884),
            arrayOf(0.0, 0.0, 0.0)
    ))


    var y = Matrix(arrayOf(
            arrayOf(4.5),
            arrayOf(7.1),
            arrayOf(6.3)
    ))
    var x = Matrix(arrayOf(
            arrayOf(0.0),
            arrayOf(0.0),
            arrayOf(0.0)
    ))
    println(original*original2)
    println(original*y)
    for (i in 0..500){
        var r=Matrix(arrayOf(
        arrayOf(0.0),
        arrayOf(0.0),
        arrayOf(0.0)
        ))
        for (k in 0..2){
            print("x[")
            print(i)
            print("]")
            print(k)
            print(" = ")
            val p = (original*original2*x) + original*y+original*original2*r
            r[k][0] = p[k][0]
        }
        x = r
        println(x)
    }



}

fun approximationPower(q: Double, norm: Double, epsilon: Double) : Int{
    var k = 0
    var approx: Double = epsilon + 1
    while (approx >= epsilon){
        k++
        approx = (q.pow(k) / (1 - q)) * norm
    }
    return k
}

fun getApproximation(k: Int, F: Matrix, f: Matrix) : Matrix{
    var prev = Matrix(f.rows, f.columns)
    var result = Matrix(f.rows, f.columns)
    var c = 0
    while(c < k){
        c++
        result = F * prev + f
        prev = result
    }
    println(c)
    return result
}

fun getFirstApproximation(F: Matrix, f: Matrix) : Matrix{
    val x_0 = Matrix(f.rows, f.columns)
    return F * x_0 + f
}

fun getZeroR(x_0: Matrix, A: Matrix, b: Matrix) : Matrix{
    return b - A * x_0
}

fun thirdNorm(vector: Matrix) : Double{
    var result = 0.0
    for (i in 0 until vector.rows){
        for(j in 0 until vector.columns){
            result += vector[i][j].pow(2)
        }
    }
    return result.pow(0.5)
}

fun getGradApproximationPower(A: Matrix, y: Matrix, m: Double, M: Double, epsilon: Double) : Int{
    var k = 0
    val x0 = Matrix(y.rows, y.columns)
    val r0 = getZeroR(x0, A, y)
    val norm = thirdNorm(r0)
    var approximation = epsilon + 1
    while (approximation >= epsilon){
        k++
        approximation = (norm / m) * ((M-m)/(M+m)).pow(k)
    }
    return k
}

fun gradDescend(k: Int, A: Matrix, b: Matrix) : Matrix{
    var result = Matrix(b.rows, b.columns)
    var x0 = Matrix(b.rows, b.columns)
    var r0 = getZeroR(x0, A, b)
    var c = 0
    var tau: Double
    while(c < k){
        x0 = result
        r0 = b - A * x0
        tau = r0.scalarTimes(r0) / r0.scalarTimes(A * r0)
        result = x0 + r0 * tau
        c++
    }
    return result
}


class Matrix(private val n: Int, private val m: Int){
    private var matrix: Array<Array<Double>> = Array(n){Array(m) {0.0} }

    val rows: Int
        get() = n

    val columns: Int
        get() = m

    constructor(arr: Array<Array<Double>>) : this(arr.size, arr[0].size){
        matrix = arr.clone()
    }

    operator fun plus(other: Matrix) : Matrix{
        if(rows == other.rows && columns == other.columns){
            val result = Matrix(rows, columns)
            for(i in 0 until rows){
                for(j in 0 until columns){
                    result[i][j] = matrix[i][j] + other[i][j]
                }
            }
            return result
        }
        else throw Exception()
    }

    operator fun get(i: Int, j: Int) : Double{
        return matrix[i][j]
    }

    operator fun get(i: Int) : Array<Double>{
        return matrix[i]
    }

    operator fun times(other: Matrix) : Matrix{
        if(m != other.matrix.size)
            throw Exception("Неподходящие размерности!")
        val result = Matrix(n, other.m)
        for (i in matrix.indices){
            for(j in other.matrix[0].indices){
                for(k in other.matrix.indices){
                    result.matrix[i][j] += matrix[i][k] * other.matrix[k][j]
                }
            }
        }
        return result
    }

    operator fun times(scal: Double) : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for(j in 0 until columns){
                result[i][j] = this[i][j] * scal
            }
        }
        return result
    }

    operator fun minus(other: Matrix) : Matrix{
        if(rows == other.rows && columns == other.columns){
            val result = Matrix(rows, columns)
            for (i in 0 until rows){
                for(j in 0 until columns){
                    result[i][j] = this[i][j] - other[i][j]
                }
            }
            return result
        }
        else throw Exception("Ошбика 00000000")
    }

    fun scalarTimes(other: Matrix) : Double{
        if(rows == other.rows && columns == other.columns){
            var result = 0.0
            for(i in 0 until rows){
                for(j in 0 until columns){
                    result += this[i][j] * other[i][j]
                }
            }
            return result
        }else throw Exception("не-не-не-не")
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for(i in matrix.indices){
            for(j in matrix[0].indices){
                sb.append("${round(matrix[i][j] * 10000) / 10000}\t\t\t")
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    fun toLowerTriangle() : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for (j in 0 until columns){
                if(j <= i){
                    result[i][j] = this[i][j]
                }
            }
        }
        return result
    }

    fun toUpperWithout() : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for(j in 0 until columns){
                if(j > i)
                    result[i][j] = this[i][j]
            }
        }
        return result
    }

    fun firstNorm() : Double{
        var result = 0.0
        var t = 0.0
        for(i in 0 until rows){
            t = 0.0
            for(j in 0 until columns){
                t += abs(this[i][j])
            }
            if(t > result)
                result = t
        }
        return result
    }

    fun secondNorm() : Double{
        var result = 0.0
        var t = 0.0
        for(i in 0 until columns){
            t = 0.0
            for(j in 0 until rows){
                t += abs(this[j][i])
            }
            if(t > result)
                result = t
        }
        return result
    }

    fun transposition() : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for(j in 0 until columns){
                result[i][j] = this[j][i]
            }
        }
        return result
    }
}