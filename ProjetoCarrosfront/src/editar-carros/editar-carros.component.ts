import { Component, OnInit } from '@angular/core';
import { CarroService } from '../service/carro.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Icarro } from '../model/Icarro'; 

@Component({
  selector: 'app-editar-carros',
  templateUrl: './editar-carros.component.html',
  styleUrls: ['./editar-carros.component.css']
})
export class EditarCarroComponent implements OnInit {
  carroSelecionado: Icarro | undefined; 
  editarCampos: boolean = false;

  constructor(
    private service: CarroService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const carId = Number(params.get('id'));
      this.service.getCarById(carId).subscribe((carro: Icarro) => {
        this.carroSelecionado = carro;
      });
    });
  }

  editarCarro(): void {
    if (!this.carroSelecionado) { 
      return;
    }

    if (this.carroSelecionado.cores?.some(cor => cor.nomeCor.trim() === '')) { 
      alert("O Campo cores não pode ser vazio");
      return;
    }

    this.service.updateCar(this.carroSelecionado!) 
      .subscribe(() => {
        alert("Carro editado");
        this.router.navigateByUrl('');
      });
  }

  cancelarEdicao(): void {
    this.editarCampos = false;
  }
}
