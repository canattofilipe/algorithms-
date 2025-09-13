package com.canattofilipe.algorithms;

class Solution {
  public String[] solve(int[] blueCosts, int[] greenCosts, int[] redCosts) {

    int lines = blueCosts.length;
    if (lines == 0) return new String[0];

    // m[i][0] = custo mínimo até o dia i se a cor do dia i for azul
    // m[i][1] = custo mínimo até o dia i se a cor do dia i for verde
    // m[i][2] = custo mínimo até o dia i se a cor do dia i for vermelho
    int[][] m = new int[lines][3];
    String[][] path = new String[lines][3];

    // Inicialização do primeiro dia
    m[0][0] = blueCosts[0];
    m[0][1] = greenCosts[0];
    m[0][2] = redCosts[0];

    path[0][0] = "b";
    path[0][1] = "g";
    path[0][2] = "r";

    for (int line = 1; line < lines; line++) {
      // Azul hoje: escolha o mínimo entre verde ou vermelho ontem
      if (m[line - 1][1] < m[line - 1][2]) {
        m[line][0] = blueCosts[line] + m[line - 1][1];
        path[line][0] = path[line - 1][1] + ",b";
      } else {
        m[line][0] = blueCosts[line] + m[line - 1][2];
        path[line][0] = path[line - 1][2] + ",b";
      }

      // Verde hoje: escolha o mínimo entre azul ou vermelho ontem
      if (m[line - 1][0] < m[line - 1][2]) {
        m[line][1] = greenCosts[line] + m[line - 1][0];
        path[line][1] = path[line - 1][0] + ",g";
      } else {
        m[line][1] = greenCosts[line] + m[line - 1][2];
        path[line][1] = path[line - 1][2] + ",g";
      }

      // Vermelho hoje: escolha o mínimo entre azul ou verde ontem
      if (m[line - 1][0] < m[line - 1][1]) {
        m[line][2] = redCosts[line] + m[line - 1][0];
        path[line][2] = path[line - 1][0] + ",r";
      } else {
        m[line][2] = redCosts[line] + m[line - 1][1];
        path[line][2] = path[line - 1][1] + ",r";
      }
    }

    // Escolhe o mínimo do último dia
    int minCost = m[lines - 1][0];
    String seq = path[lines - 1][0];
    if (m[lines - 1][1] < minCost) {
      minCost = m[lines - 1][1];
      seq = path[lines - 1][1];
    }
    if (m[lines - 1][2] < minCost) {
      minCost = m[lines - 1][2];
      seq = path[lines - 1][2];
    }

    // Converte string para array
    String[] resultSeq = seq.split(",");
    System.out.println("Custo Total Mínimo: " + minCost);
    return resultSeq;
  }

  public static void main(String[] args) {

    Solution solution = new Solution();
    int[] blueCosts = {1, 1, 1};
    int[] greenCosts = {3, 6, 7};
    int[] redCosts = {4, 6, 4};

    String[] result = solution.solve(blueCosts, greenCosts, redCosts);
    System.out.println("Sequência de Cores: " + String.join(", ", result));
  }
}
