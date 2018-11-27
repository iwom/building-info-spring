import {Floor} from './floor';

export class Building {
    public id: number;
    public name: string;
    public area: number;
    public cube: number;
    public light: number;
    public heating: number;
    public floorDtoList: Floor[];
}
