import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarroComponent } from 'src/cadastrocarro/cadastrocarro.component';
import { Formscarro } from '../formscarro/formscarro.component'; 
import { Carregando } from '../carregando/carregando.component';
import { EditarCarroComponent } from 'src/editar-carros/editar-carros.component';

const routes: Routes = [
  { path: 'cadastrocarro', component: CarroComponent }, 
  { path: 'formscarro', component: Formscarro },
  { path: 'carregando', component: Carregando },
  { path: 'editarcarros/:id', component: EditarCarroComponent }, 
  { path: '', redirectTo: '/formscarro', pathMatch: 'full' },
  { path: 'cadastrocarro', redirectTo: '/editarcarros/new', pathMatch: 'full' },
  { path: 'cadastrar', redirectTo: '/carregando', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
