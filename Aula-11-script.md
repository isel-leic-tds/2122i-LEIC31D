## Desktop Compose: Introdução
1. Demo: Criação de projecto com Desktop Compose
   1. Execução da aplicação gerada e breve descrição dos artefactos produzidos
   2. Remoção do uso de remember (fica para mais tarde)
   3. Observação do resultado da execução da aplicação modificada
2. Identificação dos elementos da solução:
   1. Funções de tratamento de eventos: onClick e onCloseRequest
   2. Funções @Composable: chamada e passagem como parâmetro
3. Modelo mental de funções @Composable
   1. State -> @Composable -> UI
   2. Primeira regra das funções @Composable: só podem ser chamadas por outras funções @Composable
4. Plugin Compose Multiplatoform para o IntelliJ
5. Modelo de threading: introdução
   1. Instrumentação do código para identificação das threads envolvidas e clarificação do fluxo de execução
   2. Caracterização do modelo de execução event driven baseado em loop de mensagens
   3. Demo: Bloqueando a main thread em I/O antes e depois da chamada a application
