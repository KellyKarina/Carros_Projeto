import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Icarro } from '../model/Icarro';
import { Icor } from '../model/Icor';
import { Imarca } from '../model/Imarca';

@Injectable({
  providedIn: 'root'
})

export class CarroService {
  private apiUrl = 'http://localhost:8080/api/carros';
  private marcaUrl = 'http://localhost:8080/api/marcas';
  private corUrl = 'http://localhost:8080/api/cores';

  listaMarcas: Imarca[] = []
  listaCarros: Icarro[] = [];
  listaCores: Icor[] = [];
  carroSelecionado!: Icarro;

  constructor(private http: HttpClient) { }

  getCars(): Observable<Icarro[]> {
    return this.http.get<Icarro[]>(this.apiUrl);
  }

  getMarcas(): Observable<Imarca[]> {
    return this.http.get<Imarca[]>(this.marcaUrl);
  }

  getCores(): Observable<Icor[]> {
    return this.http.get<Icor[]>(this.corUrl);
  }

  getCarById(carId: number): Observable<Icarro> {
    const url = `${this.apiUrl}/${carId}`;
    return this.http.get<Icarro>(url);
  }

  createCar(carro: Icarro): void {
    this.http.post(this.apiUrl, carro).subscribe({
      next: response => console.log(response)
    })
  }

  updateCar(carro: Icarro): Observable<Icarro> {
    return this.http.put<Icarro>(`${this.apiUrl}/${carro.id}`, carro);
  }

  deleteCar(carId: number): Observable<any> {
    const url = `${this.apiUrl}/${carId}`;
    return this.http.delete<any>(url);
  }

  saveCarro(carro: Icarro): Observable<any> {
    return this.http.post<any>(this.apiUrl, carro);
  }

  adicionarCor(listaCores: Icor[]): void {
    this.http.post<Icor[]>(this.apiUrl, listaCores)
  }

  buscarCores(): void {
    this.http.get<Icor[]>(this.corUrl).subscribe({
      next: (lista: Icor[]) => {
        this.listaCores = lista;
        console.log(this.listaCores);
      },
      error: error => {
        console.error('Erro ao buscar cores:', error);
      }
    })
  }

  buscarMarcas(): void {
    this.http.get<Imarca[]>(this.marcaUrl).subscribe({
      next: (lista: Imarca[]) => {
        this.listaMarcas = lista;
        console.log(this.listaMarcas);
      },
      error: error => {
        console.error('Erro ao buscar marcas:', error);
      }
    });
  }


}
