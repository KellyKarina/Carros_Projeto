import { Imarca } from "./Imarca";
import { Icor } from "./Icor";

export class Icarro {
  id: number = 0;
  nomeCarro: string;
  anoFabricacaoCarro: number;
  anoModeloCarro: number;
  modeloCarro: string;
  marca: Imarca;
  cores: Icor[];

  constructor(nomeCarro: string,
    anoFabricacaoCarro: number,
    anoModeloCarro: number,
    modeloCarro: string,
    marca: Imarca,
    cores: Icor[]) {

    this.nomeCarro = nomeCarro;
    this.anoFabricacaoCarro = anoFabricacaoCarro;
    this.anoModeloCarro = anoModeloCarro;
    this.modeloCarro = modeloCarro;
    this.marca = marca;
    this.cores = cores
  }
}
