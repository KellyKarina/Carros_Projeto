import { Component, OnInit } from '@angular/core';
import { CarroService } from '../service/carro.service';
import { Icarro } from '../model/Icarro';
import { Icor } from '../model/Icor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-formscarro',
  templateUrl: './formscarro.component.html',
  styleUrls: ['./formscarro.component.css']
})

export class Formscarro implements OnInit {
  carros: Icarro[] = [];
  dadosCarregados = false;

  constructor(private carroService: CarroService, private router: Router) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.carregarCarros();
      setTimeout(() => {
        this.dadosCarregados = true;
      }, 2000); 
    }, 2000); 
  }


  carregarCarros() {
    this.carroService.getCars().subscribe((data: Icarro[]) => {
      this.carros = data;
      this.dadosCarregados = true;
    });
  }

  editarCarro(carroId: number): void {
    this.router.navigate(['/editarcarros', carroId]);
    console.log(carroId)
  }

  navega(rota: string) {
    this.router.navigate([rota]);
  }

  excluirCarro(carroId: number): void {
    this.carroService.deleteCar(carroId).subscribe(
      () => {
        alert('Carro excluído com sucesso!');
        this.carregarCarros();
      },
      error => {
        console.error('Erro ao excluir o carro:', error);
      }
    );
  }


  formatarCores(cores: Icor[]): string {
    return cores.map(cor => cor.nomeCor).join(', ');
  }
}
