import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CarroService } from 'src/service/carro.service';
import { Icarro } from 'src/model/Icarro';
import { Imarca } from 'src/model/Imarca';
import { Icor } from 'src/model/Icor';

@Component({
  selector: 'app-cadastro-form',
  templateUrl: './cadastrocarro.component.html',
  styleUrls: ['./cadastrocarro.component.css']
})
export class CarroComponent implements OnInit {
  cadastroCarroForm!: FormGroup;
  novaCor: string = '';
  listaCores: Icor[] = [];
  nomeCarro: string = '';
  anoFabricacao: number = 0;
  anoModelo: number = 0;
  modelo: string = '';
  marcaNome: string = '';
  showNomeCarro: boolean = true;
  showAnoFabricacao: boolean = false;
  showAnoModelo: boolean = false;
  showModelo: boolean = false;
  showMarca: boolean = false;
  showCores: boolean = false;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private carroService: CarroService
  ) { }

  ngOnInit(): void {
    this.cadastroCarroForm = this.formBuilder.group({
      nomeCarro: ['', Validators.required],
      anoFabricacao: ['', Validators.required],
      anoModelo: ['', Validators.required],
      modelo: ['', Validators.required],
      marcaNome: ['', Validators.required]
    });
  }

  adicionarCor(nomeCor: string) {
    this.listaCores.push(new Icor(nomeCor));
    this.novaCor = '';
    this.atualizarExibicaoCampos();
  }


  removerCor(index: number) {
    this.listaCores.splice(index, 1);
    this.atualizarExibicaoCampos();
  }

  salvarCarro() {
    const marca = new Imarca(this.marcaNome)
    const novoCarro = new Icarro(this.nomeCarro, this.anoFabricacao, this.anoModelo, this.modelo, marca, this.listaCores)
    this.carroService.createCar(novoCarro)
    this.navega('/formscarro')

  }

  navega(rota: string) {
    this.router.navigate([rota]);
  }

  private atualizarExibicaoCampos() {
    this.showAnoFabricacao = this.listaCores.length > 0;
    this.showAnoModelo = this.listaCores.length > 1;
    this.showModelo = this.listaCores.length > 2;
    this.showMarca = this.listaCores.length > 3;
    this.showCores = this.listaCores.length > 4;
  }
}

