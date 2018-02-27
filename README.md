# Comunicação Serial COM via UDP
*WirelesSIM* é uma ferramenta simples desenvolvida em Java que converte comunicação serial, como do Arduino, para pacotes UDP em broadcast na rede.<br/>

# Motivação
Fez-se a necessidade de usar uma shield para comunicação de um Arduino em rede. Porém, sendo desenvolvida, a ferramenta conseguiu reduzir o custo da fase de testes do projeto e avaliá-lo de forma mais detalhada.<br/>

# Aplicações
Várias ferramentas para Android disponibiizam comunicação UDP com outras plataformas (destaque a DroidScript e MacroDroid), sendo assim um protocolo versátil e de vasta aplicabilidade.<br/>

# Operação UDP & Serial
A aplicação funciona monitorando atividades na Porta de Comunicação(COM) Serial, no caso para o Arduino, e as envia para endereço de broadcast de todas as interfaces de rede.<br/>
Enquanto isso, monitora atividades de rede noutra porta, para então direcioná-las ao Arduino pela mesma porta serial com o Bitrate especificado na programação.

# Dados padrão
Porta de broadcast do *WirelesSIM*: 11888.<br/>
Porta de entrada UDP do *WirelesSIM*: 11889.<br/>
Porta de comunicação é automática para dispositivos "Serial".<br/>

# Portas e Bitrate
Configurações de portas dos protocolos e do bitrate são feitas no arquivo *Main.java*.<br/>
Quaisquer outras alterações podem ser feitas nos respectivos arquivos.<br/>

# Modificações no código:
É necessário um contínuo estudo do código, uma vez que o desempenho pode ser melhorado.<br/>
*Favor referir o repositório original.*<br/>

